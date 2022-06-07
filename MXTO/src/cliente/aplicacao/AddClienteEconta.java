package cliente.aplicacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ClientesFDAO;
import dto.ClientesDTO;
import dto.ContaDTO;



public class AddClienteEconta {


	public static void main(String[] args) throws Exception {
		
		
		ClientesFDAO clientesDAO = new ClientesFDAO();
		 
		
	    // Cria um cliente
		ClientesDTO cliente = new ClientesDTO();
		cliente.setNome_cliente_F("Pedro Paulo");
		cliente.setCpf("551.222.444-20");
		cliente.setCelular("87412-85214");
		cliente.setEmail("robertamaria@gmail.com");
		cliente.setEndereco("Av Parnamirim");
		cliente.setNum_end("555");
		cliente.setBairro("Parnamirim");
		cliente.setCidade("Recife");
		cliente.setEstado("Pernambuco");
		cliente.setData_cad_F(new Date());
		cliente.setCategoria("Pessoa Fisica");
		
		// Cria conta vinculada ao cpf do cliente
		ContaDTO conta = new ContaDTO();
		conta.setSaldo(7542.32);
		conta.setTipoConta("Pessoa Fisica");
		conta.setTaxa(0.75);
		conta.setCPF(cliente.getCpf());
		 
		List<ContaDTO> contasDTO = new ArrayList<ContaDTO>();
		 
		contasDTO.add(conta);	
		 
		cliente.setContacliente(contasDTO);

		try {
			clientesDAO.salvar(cliente);
			System.out.printf("Cliente: %s adicionado(a) com sucesso!\nConta cadastro: %s", cliente.getNome_cliente_F(), conta.getTipoConta());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}	

	}
}