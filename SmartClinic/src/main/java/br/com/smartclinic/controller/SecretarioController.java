package br.com.smartclinic.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;

import br.com.smartclinic.RegraNegocioException;
import br.com.smartclinic.model.Endereco;
import br.com.smartclinic.model.Medico;
import br.com.smartclinic.model.Pessoa;
import br.com.smartclinic.model.Secretario;
import br.com.smartclinic.model.Telefone;
import br.com.smartclinic.model.Usuario;
import br.com.smartclinic.model.enums.SexoEnum;
import br.com.smartclinic.model.enums.TipoEnderecoEnum;
import br.com.smartclinic.service.SecretarioService;

@ManagedBean
@ViewScoped
public class SecretarioController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Secretario actionSecretario;
	private List<Secretario> secretarioList;
	private SecretarioService secretarioService;
	private Secretario secretario;
	
	@ManagedProperty(value = "#{telefoneController.telefones}")
	private List<Telefone> telefones;
	
	@ManagedProperty("#{enderecoController.enderecos}")
	private List<Endereco> enderecos;
	
	@ManagedProperty(value = "#{usuarioController.usuario}")
	private Usuario usuario;
	
	@PostConstruct
	public void init(){
		secretarioService = SecretarioService.getInstance();
		
		secretario = new Secretario();
		secretario.setPessoa(new Pessoa());
	}
	
	public void setActionSecretario(Secretario secretario){
		actionSecretario = secretario;
	}

	public List<Secretario> getSecretarioList() {
		if(secretarioList == null){
			secretarioList = secretarioService.listar(new Secretario());
		}
		return secretarioList;
	}

	public void setSecretarioList(List<Secretario> secretarioList) {
		this.secretarioList = secretarioList;
	}
	
	public String excluir(){
		if(actionSecretario != null){
			System.out.println("Funcionou: " + actionSecretario.getPessoa().getNome());
		}
		return "public/secretarioListar";
	}
	
	public String onFlowProcess(FlowEvent event){
		return event.getNewStep();
	}
	
	public SexoEnum[] getSexos(){
		return SexoEnum.values();
	}

	public TipoEnderecoEnum[] getTiposEndereco(){
		return TipoEnderecoEnum.values();
	}
	
	public Secretario getSecretario() {
		return secretario;
	}

	public void setSecretario(Secretario secretarioParaInserir) {
		this.secretario = secretarioParaInserir;
	}
	
	public String incluirMedico(){
		secretario.getPessoa().setEnderecos(enderecos);
		secretario.getPessoa().setTelefones(telefones);
		secretario.setUsuario(usuario);
		
		try{
			secretarioService.inserir(secretario, true);
		}catch(RegraNegocioException e){
			System.out.println(e.getMensagens());
		}
		return "";
	}
	
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
