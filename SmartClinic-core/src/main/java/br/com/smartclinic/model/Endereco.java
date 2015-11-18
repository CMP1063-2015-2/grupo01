package br.com.smartclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "ENDERECO")
public class Endereco implements TransferEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_endereco")
	private Long id;
	
	@Column(name = "logradouro", nullable = false, length = 255)
	private String logradouro;
	
	@Column(name = "numero", nullable = false, length = 10)
	private String numero;
	
	@Column(name = "bairro", nullable = false, length = 100)
	private String bairro;
	
	@Column(name = "complemento", nullable = false, length = 100)
	private String complemento;
	
	@Column(name = "cep", nullable = false, length = 9)
	private String cep;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_cidade", nullable = false)
	private Cidade cidade;

	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
}
