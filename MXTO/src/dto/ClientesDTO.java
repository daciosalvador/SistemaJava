package dto;

import java.util.Date;
import java.util.List;

public class ClientesDTO{
	
	private Integer id_cliente_F;
    private String nome_cliente_F;
    private String cpf;
    private String celular;
    private String email;
    private String endereco;
    private String num_end;
    private String bairro;
    private String cidade;
    private String estado;
    private Date data_cad_F;
    private String categoria;
    private List<ContaDTO> contacliente;
	
	
	public Integer getId_cliente_F() {
		return id_cliente_F;
	}
	public void setId_cliente_F(Integer id_cliente_F) {
		this.id_cliente_F = id_cliente_F;
	}
	public String getNome_cliente_F() {
		return nome_cliente_F;
	}
	public void setNome_cliente_F(String nome_cliente_F) {
		this.nome_cliente_F = nome_cliente_F;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNum_end() {
		return num_end;
	}
	public void setNum_end(String num_end) {
		this.num_end = num_end;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getData_cad_F() {
		return data_cad_F;
	}
	public void setData_cad_F(Date data_cad_F) {
		this.data_cad_F = data_cad_F;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public List<ContaDTO> getContacliente() {
		return contacliente;
	}
	public void setContacliente(List<ContaDTO> contacliente) {
		this.contacliente = contacliente;
	}	
	
	
	

}
