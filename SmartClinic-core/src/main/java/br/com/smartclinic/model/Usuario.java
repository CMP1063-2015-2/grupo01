package br.com.smartclinic.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.smartclinic.model.enums.TipoUsuarioEnum;

@Entity(name = "USUARIO")
public class Usuario implements TransferEntity, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario")
	private Long id;
	
	@Column(name = "login", length = 255, nullable = false, unique = true)
	private String login;
	
	@Column(name = "senha", length = 255, nullable = false)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_usuario", nullable = false)
	private TipoUsuarioEnum tipoUsuario;
	
	@OneToOne(mappedBy = "usuario")
	private Medico medico;
	
	@Override
	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoUsuarioEnum getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	public String getNome(){
		String nome = "";
		if(medico != null && medico.getPessoa() != null){
			nome = medico.getPessoa().getNome();
		}
		return nome;
	}
}
