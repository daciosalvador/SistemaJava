package cliente.aplicacao;

import java.util.Date;

import dao.ClientesFDAO;
import dto.ClientesDTO;



public class AddCliente {


	public static void main(String[] args) throws Exception {
		
		ClientesFDAO clientesDAO = new ClientesFDAO();
		 
		
		 //Cria um contato e salva no banco
		 ClientesDTO clientes = new ClientesDTO();
		 clientes.setNome_cliente_F("PedroHenrique");
		 clientes.setCpf("321.555.123-22");
		 clientes.setCelular("98423-45632");
		 clientes.setEmail("henriquepaulo@gmail.com");
		 clientes.setEndereco("Av Mario Melo");
		 clientes.setNum_end("555");
		 clientes.setBairro("Boa Viagem");
		 clientes.setCidade("Recife");
		 clientes.setEstado("Pernambuco");
		 clientes.setData_cad_F(new Date());
		 clientes.setCategoria("Pessoa Fisica");
		 
		// ContaDAO contaDAO = new ContaDAO();
		// contaDAO.salvarConta(clientes.getContacliente());
		 
		 
		 clientes.setContacliente(null);
		 
		 
		 
		 clientesDAO.salvar(clientes);	
		 
	 	

	}
}