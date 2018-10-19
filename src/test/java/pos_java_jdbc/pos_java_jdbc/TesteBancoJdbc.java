package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import dao.UserPosDAO;
import model.Userposjava;

public class TesteBancoJdbc {
	
	@Test
	public void initBanco() {	
		
		UserPosDAO userPosDAO = new UserPosDAO();
		Userposjava userposjava = new Userposjava("joao da silva","emaildojoao@jaoo.com");

		userPosDAO.salvar(userposjava);
		
		
	}
	
	@Test
	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<Userposjava> lista = dao.listar();
			
			for(Userposjava item : lista) {
				System.out.println(item.getNome());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void initBuscar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			Userposjava userposjava = dao.buscar(10L);
			System.out.println(userposjava.getNome()+"\n"+userposjava.getId().toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void initAtualizar() {
		
		try {
			UserPosDAO dao = new UserPosDAO();
			
			Userposjava objBanco = dao.buscar(4L);
			objBanco.setNome("Nome atualizado com metodo atualizar");
			
			dao.atualizar(objBanco);
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
	}
	
	@Test
	public void initDeletar() {
		try {
			UserPosDAO dao = new UserPosDAO();
			dao.deletar(6L);
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
	}
	
	
	
	
	

}
