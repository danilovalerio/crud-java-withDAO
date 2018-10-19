package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5432/posjava";
	private static String senha = "admin";
	private static String user = "postgres";
	private static Connection conn = null;
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	
	public static void conectar() {
		try {
			
			if(conn == null) {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, user, senha);
				conn.setAutoCommit(false);
				System.out.println("Conectou com sucesso!");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		return conn;
	}

}
