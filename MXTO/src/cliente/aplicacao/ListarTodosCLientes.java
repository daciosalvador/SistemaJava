package cliente.aplicacao;

import java.text.DateFormat;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;

import java.util.List;

import dao.ClientesFDAO;
import dto.ClientesDTO;
import dto.ContaDTO;

public class ListarTodosCLientes {	
	//Lista todos os clientes
	 public static void main(String[] args) {
		 
		 DecimalFormat df = new DecimalFormat("###,###.00");
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 
		 int numCols = 10;
		 String[][] listaRetorno = null;
		 
		 ClientesFDAO pessoaDAO = new ClientesFDAO();
		 
		 List<ClientesDTO> lista = pessoaDAO.getClientes();		 
		 List<ContaDTO> listConta = pessoaDAO.getConta();
		
		listaRetorno = new String[lista.size()][numCols];
			
		 for (int i = 0; i < lista.size(); i++) {
			 ClientesDTO pessoa = lista.get(i);
				ContaDTO conta = listConta.get(i);
				
				listaRetorno[i][0] = pessoa.getId_cliente_F().toString();
				listaRetorno[i][1] = pessoa.getNome_cliente_F();
				listaRetorno[i][2] = pessoa.getCpf();
				listaRetorno[i][3] = dateFormat.format(pessoa.getData_cad_F());
				listaRetorno[i][4] = df.format(conta.getSaldo());
				
				
				System.out.printf("Cliente: %s - Cliente desde: %s - Saldo em %s: R$ %s \n", listaRetorno[i][1], 
						listaRetorno[i][3], 
						dtf.format(LocalDateTime.now()), listaRetorno[i][4]);
						 
		 }		 
	 }
}
