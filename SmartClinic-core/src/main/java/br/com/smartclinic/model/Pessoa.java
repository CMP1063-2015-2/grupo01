package br.com.smartclinic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import br.com.smartclinic.model.enums.SexoEnum;

@Entity(name="PESSOA")
public class Pessoa implements TransferEntity{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_pessoa")
	private Long id;

	@Column(name = "nome", length = 255, nullable = false)
	private String nome;
	
	@Column(name = "cpf", length = 14, nullable = false)
	private String cpf;
	
	@Column(name = "rg", length = 255, nullable = false)
	private String rg;
	
	@Enumerated
	@Column(name = "sexo", nullable = false)
	private SexoEnum sexo;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "PESSOA_TELEFONE", joinColumns = @JoinColumn(name = "id_medico"), inverseJoinColumns = @JoinColumn(name = "id_telefone"))
	private List<Telefone> telefones;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
}
