package cliente.aplicacao;


import java.text.DecimalFormat;
import java.util.List;

import dao.ClientesFDAO;
import dto.ClientesDTO;
import dto.ContaDTO;

public class ListarClienteX {
	// Lista Cliente e conta vinculada pelo CPF
	public static void main(String[] args) throws Exception {
		
		DecimalFormat df = new DecimalFormat("###,###.00");
		ClientesFDAO clientesDAO = new ClientesFDAO();

		ClientesDTO c = clientesDAO.getCliente("104.345.432-23"); // Alterar cpf para localizar cliente

		System.out.println("NOME: " + c.getNome_cliente_F());
		System.out.println("Cliente Desde: " + c.getData_cad_F());
		System.out.printf("Rua:%s Numero: %s Bairro: %s Cidade: %s Estado: %s\n",c.getEndereco(),c.getNum_end(),
				c.getBairro(), c.getCidade(), c.getEstado());

		List<ContaDTO> contas = c.getContacliente();
	
		
		double saldo = 0;

		for (ContaDTO cc : contas) {
			
			System.out.println("Num Conta: " + cc.getId_conta());
			saldo = cc.getSaldo();
			System.out.println("Saldo: R$ " + df.format(saldo));
			
		}
		System.out.println("------------------------------------------------------------------------------------------");
		
		

	}

}
