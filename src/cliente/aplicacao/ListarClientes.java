package cliente.aplicacao;

import java.util.Date;
import java.util.List;

import dao.ClientesFDAO;
import dto.ClientesDTO;
import dto.ContaDTO;

public class ListarClientes {

	public static void main(String[] args) {

		ClientesFDAO clientesDAO = new ClientesFDAO();

		// Cria um contato e salva no banco
		ClientesDTO cliente = new ClientesDTO();
		cliente.setNome_cliente_F("PedroHenrique");
		cliente.setCpf("321.555.123-22");
		cliente.setCelular("98423-45632");
		cliente.setEmail("henriquepaulo@gmail.com");
		cliente.setEndereco("Av Mario Melo");
		cliente.setNum_end("555");
		cliente.setBairro("Boa Viagem");
		cliente.setCidade("Recife");
		cliente.setEstado("Pernambuco");
		cliente.setData_cad_F(new Date());
		cliente.setCategoria("Pessoa Fisica");

		try {
			clientesDAO.salvar(cliente);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ClientesDTO c = clientesDAO.getCliente(cliente.getCpf());

		System.out.println("NOME: " + c.getNome_cliente_F());
		System.out.println("Cliente Desde: " + c.getData_cad_F());

		List<ContaDTO> contas = c.getContacliente();

		double saldo = 0;

		for (ContaDTO cc : contas) {

			saldo += cc.getSaldo();

		}

		System.out.println("Saldo: " + saldo);
		System.out.println("------------------------------");

	}

}
