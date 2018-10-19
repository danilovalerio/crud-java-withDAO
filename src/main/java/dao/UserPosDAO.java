package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.Userposjava;

public class UserPosDAO {
	
	private Connection conexao;
	
	public UserPosDAO() {
		conexao = SingleConnection.getConnection();
	}
	
	public void salvar (Userposjava userposjava) {
		try {
			String sql = "insert into userposjava (nome, email) values (?, ?)";
			PreparedStatement insert = conexao.prepareStatement(sql);
			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.execute();
			conexao.commit(); //salva no banco
			
		} catch (Exception e) {
			try {
				conexao.rollback(); //reverte qualquer alteração no banco
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public List<Userposjava> listar(){
		List <Userposjava> lista = new ArrayList<>();
		
		String sql = "select * from userposjava order by nome";
		
		try {
			PreparedStatement st = conexao.prepareStatement(sql);
			ResultSet resultado = st.executeQuery();
			
			while(resultado.next()) { //enquanto tiver dados adiciona na lista
				
				Userposjava userposjava = new Userposjava();
				userposjava.setId(resultado.getLong("id"));
				userposjava.setNome(resultado.getString("nome"));
				userposjava.setEmail(resultado.getString("email"));
				
				lista.add(userposjava);
				
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	/**
	 * @param id
	 * @return
	 */
	public Userposjava buscar(Long id){
		Userposjava retorno = new Userposjava();
		
		String sql = "select * from userposjava where id = " + id;
		
		try {
			PreparedStatement st = conexao.prepareStatement(sql);
			ResultSet resultado = st.executeQuery();
			
			while(resultado.next()) { //retorn apenas um usuário ou nenhum
				
				retorno.setId(resultado.getLong("id"));
				retorno.setNome(resultado.getString("nome"));
				retorno.setEmail(resultado.getString("email"));				
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}
	
	public void atualizar(Userposjava userposjava) {
		
		try {
			
			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();
			
			PreparedStatement st = conexao.prepareStatement(sql);
			st.setString(1, userposjava.getNome());
			st.execute();
			conexao.commit();
			
			System.out.println("Dados atualizados com sucesso!");
		} catch (SQLException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void deletar(Long id) {
		try {
			String sql = "delete from userposjava where id = "+ id;
			
			PreparedStatement st = conexao.prepareStatement(sql);
			st.execute();		
			conexao.commit();
			
		} catch (Exception e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	

}
