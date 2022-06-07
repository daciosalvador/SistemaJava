package javaConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBdo {
	
	private static final String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String username = "system";
	private static final String  password = "tetu7634092";
	
	public static Connection creatConnectionToOracle() throws Exception {
		
				
		Connection connection = DriverManager.getConnection(dbURL, username, password);
		
		return connection;
	}

	public static void main(String[] args) throws Exception { 				

		Connection con = creatConnectionToOracle();
		
		if(con != null) {
			System.out.println("Conexão feita com sucesso");
			con.close();
		}
	}

}
