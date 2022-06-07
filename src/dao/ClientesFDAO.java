package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.ClientesDTO;
import dto.ContaDTO;
import javaConnection.ConnectionBdo;

public class ClientesFDAO {
	Connection con = null;
	PreparedStatement pstm = null;
	

	public void salvar(ClientesDTO clientes) throws Exception {
		
		//int chaveConta = salvarConta(clientes.getContacliente());

		con = ConnectionBdo.creatConnectionToOracle();
		String sql = "INSERT INTO clientesf(nome_cliente_f, cpf, celular, email, endereco, num_end, bairro, cidade, estado, data_cad_F, categoria, id_conta) "
				+ "VALUES (?, ? ,? , ?, ?, ?, ?, ?, ?, ?, ?, ?)";		
		
		
		try {			
			
			pstm = con.prepareStatement(sql);			
			pstm.setString(1, clientes.getNome_cliente_F());
			pstm.setString(2, clientes.getCpf());			
			pstm.setString(3, clientes.getCelular());
			pstm.setString(4, clientes.getEmail());
			pstm.setString(5, clientes.getEndereco());
			pstm.setString(6, clientes.getNum_end());
			pstm.setString(7, clientes.getBairro());
			pstm.setString(8, clientes.getCidade());
			pstm.setString(9, clientes.getEstado());
			pstm.setDate(10,  new Date(clientes.getData_cad_F().getTime()));
			pstm.setString(11, clientes.getCategoria());
			
			
			
			pstm.execute();	
			pstm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public int salvarConta(ContaDTO contacliente) throws Exception{
		
		int chave = 0;
		con = ConnectionBdo.creatConnectionToOracle();
		String sql = "INSERT INTO conta(saldo, tipoconta, taxa, idCliente) "
				+ "VALUES (?, ? ,? , ?)";	
		
		
		try {			
			
			
			pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//pstm.setInt(4, idCliente);
			pstm.setDouble(1, contacliente.getSaldo());
			pstm.setString(2, contacliente.getTipoConta());
			pstm.setDouble(3, contacliente.getTaxa());						
			pstm.execute();	
			ResultSet result = pstm.getGeneratedKeys();
			
			if(result.next()) {
				chave = result.getInt(1);
			}
			
			pstm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chave;		
	}
	
	public void update(ClientesDTO clientes) throws Exception {		
		
		
		
		String sql = "UPDATE clientesf SET nome_cliente_f = ?, celular = ?, email = ?"
				+ "WHERE id_cliente_f = ?";		
		
		
		try {			
			con = ConnectionBdo.creatConnectionToOracle();
			pstm = con.prepareStatement(sql);			
			pstm.setString(1, clientes.getNome_cliente_F());			
			pstm.setString(2, clientes.getCelular());
			pstm.setString(3, clientes.getEmail());			
	        pstm.setInt(4, clientes.getId_cliente_F());
			
			
			pstm.execute();
			pstm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	 

	}
	public void updateEndereco(ClientesDTO clientes) throws Exception {		
		
		
		String sql = "UPDATE clientesf SET endereco = ?, num_end = ?, bairro = ?, cidade = ?, estado = ?"
				+ "WHERE id_cliente_F = ?";		
		
		
		try {			
			con = ConnectionBdo.creatConnectionToOracle();
			pstm = con.prepareStatement(sql);			
			pstm.setString(1, clientes.getEndereco());			
			pstm.setString(2, clientes.getNum_end());
			pstm.setString(3, clientes.getBairro());
			pstm.setString(4, clientes.getCidade());
			pstm.setString(5, clientes.getEstado());
	        pstm.setLong(6, clientes.getId_cliente_F());
			
			
			pstm.execute();	
			pstm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ClientesDTO getCliente(String cpf) {
		
		String sql = "SELECT id_cliente_f, nome_cliente_f,  FROM ClientesF WHERE CPF = ?";
		
		ResultSet rs = null;
		ClientesDTO cliente = new ClientesDTO();
		
		try {
			con = ConnectionBdo.creatConnectionToOracle();
			pstm.setString(1, cpf);
			pstm = con.prepareStatement(sql);
			
			con = ConnectionBdo.creatConnectionToOracle();
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
				
			cliente.setId_cliente_F(rs.getInt("id_cliente_f"));
			cliente.setNome_cliente_F(rs.getString("nome_cliente_f"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setCelular(rs.getString("celular"));
			cliente.setData_cad_F(rs.getDate("data_cad_f"));;
			
			sql = "SELECT * FROM CONTA WHERE idCliente = ?";
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			List<ContaDTO> contas = new ArrayList<>();
			
			while(rs.next()) {
				
				ContaDTO c = new ContaDTO();
				
				c.setId_conta(rs.getInt("id_conta"));
				c.setIdCliente(rs.getInt("id_cliente"));
				c.setSaldo(rs.getDouble("saldo"));
				c.setTipoConta(rs.getString("tipoconta"));
				c.setTaxa(rs.getDouble("taxa"));
				
				contas.add(c);
				
			}
			
			cliente.setContacliente(contas);

				//cliente.setContacliente(buscaContaPorId(rs.getInt("id_conta")));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cliente;
	}	
	
	public List<ClientesDTO> getClientes() {	
		
		String sql = "SELECT * FROM ClientesF";	
		List<ClientesDTO> clientes = new ArrayList<ClientesDTO>();
		ResultSet rs = null;		
	
		try {				
			
			con = ConnectionBdo.creatConnectionToOracle();
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				
				ClientesDTO cliente = new ClientesDTO();
				
				cliente.setId_cliente_F(rs.getInt("id_cliente_f"));
				cliente.setNome_cliente_F(rs.getString("nome_cliente_f"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setCelular(rs.getString("celular"));
				cliente.setData_cad_F(rs.getDate("data_cad_f"));;
				//cliente.setContacliente(buscaContaPorId(rs.getInt("id_conta")));
				
				
				clientes.add(cliente);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return clientes;		 

	}
	public ContaDTO buscaContaPorId(int id_conta) {
		ContaDTO contas = null;
		
		try{
			con = ConnectionBdo.creatConnectionToOracle();
			String sql = "SELECT * from CONTA WHERE id_conta = ?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id_conta);
			ResultSet res = pstm.executeQuery();
			if(res.next()){
				contas = new ContaDTO();
				contas.setId_conta(res.getInt(1));
				contas.setSaldo(res.getDouble(2));
				contas.setTipoConta(res.getString(3));
				contas.setTaxa(res.getDouble(4));				

			}
			con.close();
		}catch(Exception e){
			e.printStackTrace();
			
		}
		return contas;

	}


}
