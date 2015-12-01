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
import br.com.smartclinic.model.Telefone;
import br.com.smartclinic.model.Usuario;
import br.com.smartclinic.model.enums.SexoEnum;
import br.com.smartclinic.model.enums.TipoEnderecoEnum;
import br.com.smartclinic.service.MedicoService;

@ManagedBean
@ViewScoped
public class MedicoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Medico actionMedico;
	private List<Medico> medicoList;
	private MedicoService medicoService;
	private Medico medico;
	
	@ManagedProperty(value = "#{telefoneController.telefones}")
	private List<Telefone> telefones;
	
	@ManagedProperty("#{enderecoController.enderecos}")
	private List<Endereco> enderecos;
	
	@ManagedProperty(value = "#{usuarioController.usuario}")
	private Usuario usuario;
	
	@PostConstruct
	public void init(){
		medicoService = MedicoService.getInstance();
		
		medico = new Medico();
		medico.setPessoa(new Pessoa());
	}
	
	public void setActionMedico(Medico medico){
		actionMedico = medico;
	}

	public List<Medico> getMedicoList() {
		if(medicoList == null){
			medicoList = medicoService.listar(new Medico());
		}
		return medicoList;
	}

	public void setMedicoList(List<Medico> medicoList) {
		this.medicoList = medicoList;
	}
	
	public String excluir(){
		if(actionMedico != null){
			System.out.println("Funcionou: " + actionMedico.getPessoa().getNome());
		}
		return "public/medicoListar";
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
	
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medicoParaInserir) {
		this.medico = medicoParaInserir;
	}
	
	public String incluirMedico(){
		medico.getPessoa().setEnderecos(enderecos);
		medico.getPessoa().setTelefones(telefones);
		medico.setUsuario(usuario);
		
		try{
			medicoService.inserir(medico, true);
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
