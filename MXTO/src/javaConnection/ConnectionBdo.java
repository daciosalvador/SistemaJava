package javaConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBdo {
	
	private static final String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String username = "system";
	private static final String  password = "tetu7634092";
	private static Connection connection;
	
	public static Connection creatConnectionToOracle(){
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(dbURL, username, password);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return connection;
	}

//	public static void main(String[] args) throws Exception { 				
//
//		Connection con = creatConnectionToOracle();
//		
//		if(con != null) {
//			System.out.println("Conexão feita com sucesso");
//			con.close();
//		}
//	}

}
