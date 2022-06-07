package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.ContaDTO;
import javaConnection.ConnectionBdo;

public class ContaDAO {
	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet rs;

	public void salvarConta(ContaDTO contas, int idCliente) throws Exception {

		String sql = "INSERT INTO conta(saldo, tipoconta, taxa, idCliente) "
				+ "VALUES (?, ? ,? ,)";		
		
		
		try {			
			
			con = ConnectionBdo.creatConnectionToOracle();
			pstm = con.prepareStatement(sql);			
			pstm.setDouble(1, contas.getSaldo());
			pstm.setString(2, contas.getTipoConta());
			pstm.setDouble(3, contas.getTaxa());
			
			
			pstm.execute();	
			pstm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
