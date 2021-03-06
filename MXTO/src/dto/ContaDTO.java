package dto;



public class ContaDTO {
	
	private int id_conta;
	private Double saldo;	
	private String tipoConta;
	private Double taxa;
	private String CPF;
	
	public int getId_conta() {
		return id_conta;
	}
	public void setId_conta(int id_conta) {
		this.id_conta = id_conta;
	}
	
	public String getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public Double getTaxa() {
		return taxa;
	}
	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	
	public void deposito (double valor) {
		this.setSaldo(this.getSaldo() + valor);
	}
	
	public boolean saque (double valor) {
		if(this.getSaldo() >= valor) {
			this.setSaldo(this.getSaldo() - valor);
			return true;
		}
		return false;
	}
	

}
