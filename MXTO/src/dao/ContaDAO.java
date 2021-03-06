package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.ContaDTO;
import javaConnection.ConnectionBdo;

public class ContaDAO {
	Connection con = ConnectionBdo.creatConnectionToOracle();
	PreparedStatement pstm = null;
	ResultSet rs;

	public void salvarConta(ContaDTO contas) throws Exception {

		String sql = "INSERT INTO conta(ID_CONTA, saldo, tipoconta, taxa, cpf) VALUES (SEQ_ID_CONTA.NEXTVAL, ?, ?, ?, ?)";		
		
		
		try {			
			
			con = ConnectionBdo.creatConnectionToOracle();
			PreparedStatement pstm = con.prepareStatement(sql);			
			pstm.setDouble(1, contas.getSaldo());
			pstm.setString(2, contas.getTipoConta());
			pstm.setDouble(3, contas.getTaxa());	
			pstm.setString(4, contas.getCPF());		

			
			pstm.execute();	
			pstm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
