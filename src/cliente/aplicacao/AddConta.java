package cliente.aplicacao;


import dao.ContaDAO;
import dto.ContaDTO;


public class AddConta {
	
	public static void main(String[] args) throws Exception {
		
		@SuppressWarnings("unused")
		ContaDAO contaD = new ContaDAO();
		 
		 //Cria um contato e salva no banco
		 ContaDTO contas = new ContaDTO();
		 contas.setSaldo(1556.32);
		 contas.setTipoConta("Pessoa Fisica");
		 contas.setTaxa(0.75);
		 
		 
		 //contaD.salvarConta(contas); 

	}
}

