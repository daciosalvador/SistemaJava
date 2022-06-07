package cliente.aplicacao;

import dao.ClientesFDAO;
import dto.ClientesDTO;


public class UpdateCliente {
	
	public static void main(String[] args) throws Exception {
	
	
	ClientesFDAO clientesDAO = new ClientesFDAO();
	
	 ClientesDTO clientes1 = new ClientesDTO();
	 clientes1.setId_cliente_F(1); // ID do cliente que deseja alterar
	 clientes1.setNome_cliente_F("Marcos Antonio"); 
	 clientes1.setCelular("8852-55212");
	 clientes1.setEmail("marcos_antonio@gmail.com");
	 
	 clientesDAO.update(clientes1);
	 
	}
	
}
