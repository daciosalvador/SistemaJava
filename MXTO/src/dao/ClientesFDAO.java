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
	
	//Salvar Cliente
	public void salvar(ClientesDTO clientes) throws Exception {
		
		

		con = ConnectionBdo.creatConnectionToOracle();
		String sql = "INSERT INTO clientesf(ID_CLIENTE_F, nome_cliente_f, cpf, celular, email, endereco, num_end, bairro, cidade, estado, data_cad_F, categoria) "
				+ "VALUES (SEQ_CLIENTE_F.nextval ,?, ? ,? , ?, ?, ?, ?, ?, ?, ?, ?)";		
		
		
		try {			
			
			PreparedStatement pstm = con.prepareStatement(sql);			
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
			
			ContaDAO cDAO = new ContaDAO();
			
			for(ContaDTO c : clientes.getContacliente()) {
				
				
				cDAO.salvarConta(c);
				
			}
			
			pstm.execute();	
			pstm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	//Salvar Conta
	public int salvarConta(ContaDTO contacliente) throws Exception{
		
		int chave = 0;
		con = ConnectionBdo.creatConnectionToOracle();
		String sql = "INSERT INTO conta(saldo, tipoconta, taxa, idCliente) "
				+ "VALUES (?, ? ,? , ?)";	
		
		
		try {			
			
			
			PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
	
	// Alterar Cliente
	public void update(ClientesDTO clientes) throws Exception {		
		
		
		
		String sql = "UPDATE clientesf SET nome_cliente_f = ?, celular = ?, email = ?"
				+ "WHERE id_cliente_f = ?";		
		
		
		try {			
			con = ConnectionBdo.creatConnectionToOracle();
			PreparedStatement pstm = con.prepareStatement(sql);			
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
	//Alterar endereco cliente
	public void updateEndereco(ClientesDTO clientes) throws Exception {		
		
		
		String sql = "UPDATE clientesf SET endereco = ?, num_end = ?, bairro = ?, cidade = ?, estado = ?"
				+ "WHERE id_cliente_F = ?";		
		
		
		try {			
			con = ConnectionBdo.creatConnectionToOracle();
			PreparedStatement pstm = con.prepareStatement(sql);			
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
	
	// Selecionar dados do cliente
	public ClientesDTO getCliente(String cpf) {
		
		String sql = "SELECT id_cliente_f, nome_cliente_f, CPF, data_cad_f, celular, bairro, endereco, cidade, num_end, estado FROM ClientesF WHERE CPF = ?";
		
		ResultSet rs = null;
		ClientesDTO cliente = new ClientesDTO();
		
		try {
			con = ConnectionBdo.creatConnectionToOracle();
			
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, cpf);
		
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				
				cliente.setId_cliente_F(rs.getInt("id_cliente_f"));
				cliente.setNome_cliente_F(rs.getString("nome_cliente_f"));
				cliente.setCpf(rs.getString("CPF"));
				cliente.setCelular(rs.getString("celular"));
				cliente.setData_cad_F(rs.getDate("data_cad_f"));;
				cliente.setBairro(rs.getString("bairro"));				
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setCidade(rs.getString("cidade"));
				cliente.setNum_end(rs.getString("num_end"));
				cliente.setEstado(rs.getString("estado"));
				
			
			}
			
			sql = "SELECT * FROM CONTA WHERE cpf = ?";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, cliente.getCpf());
			rs = pstm.executeQuery();
			
			List<ContaDTO> contas = new ArrayList<>();
			
			while(rs.next()) {
				
				ContaDTO c = new ContaDTO();
				
				c.setId_conta(rs.getInt("id_conta"));
				c.setCPF(rs.getString("cpf"));
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
	
	//Listar Cliente
	public List<ClientesDTO> getClientes() {	
		
		String sql = "SELECT * FROM ClientesF";	
		List<ClientesDTO> clientes = new ArrayList<ClientesDTO>();
		ResultSet rs = null;		
	
		try {				
			
			con = ConnectionBdo.creatConnectionToOracle();
			PreparedStatement pstm = con.prepareStatement(sql);
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
	//Buscar Conta por ID
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
	//Listar Contas
	public List<ContaDTO> getConta() {	
		
		String sql = "SELECT * FROM Conta";	
		List<ContaDTO> contas = new ArrayList<ContaDTO>();
		ResultSet rs = null;		
	
		try {				
			
			con = ConnectionBdo.creatConnectionToOracle();
			PreparedStatement pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()){
				
				ContaDTO conta = new ContaDTO();
				
				conta.setId_conta(rs.getInt("id_conta"));
				conta.setSaldo(rs.getDouble("saldo"));
				conta.setCPF(rs.getString("cpf"));
				conta.setTipoConta(rs.getString("tipoconta"));
				
				
				contas.add(conta);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return contas;		 

	}
	
	//Alterar Saldo da Conta 
	public void updateConta(ContaDTO conta) throws Exception {		
		
		
		
		String sql = "UPDATE Conta SET saldo = ?"
				+ "WHERE id_conta = ?";		
		
		
		try {			
			con = ConnectionBdo.creatConnectionToOracle();
			PreparedStatement pstm = con.prepareStatement(sql);			
			pstm.setDouble(1, conta.getSaldo());
			pstm.setInt(2, conta.getId_conta());
			
			
			
			pstm.execute();
			pstm.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	 

	}


}
