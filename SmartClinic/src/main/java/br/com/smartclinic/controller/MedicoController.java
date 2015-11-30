package br.com.smartclinic.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import br.com.smartclinic.model.Cidade;
import br.com.smartclinic.model.Endereco;
import br.com.smartclinic.model.Medico;
import br.com.smartclinic.model.Pessoa;
import br.com.smartclinic.model.Telefone;
import br.com.smartclinic.model.Usuario;
import br.com.smartclinic.model.enums.SexoEnum;
import br.com.smartclinic.model.enums.TipoEnderecoEnum;
import br.com.smartclinic.service.CidadeService;
import br.com.smartclinic.service.MedicoService;
import br.com.smartclinic.utils.Util;

@ManagedBean
@ViewScoped
public class MedicoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Medico actionMedico;
	private List<Medico> medicoList;
	private MedicoService medicoService;
	private List<Endereco> enderecos;
	private List<Cidade> cidades;
	private Medico medico;
	private Endereco endereco;
	
	@PostConstruct
	public void init(){
		medicoService = MedicoService.getInstance();
		cidades = CidadeService.getInstance().listar(new Cidade(), true);
		endereco = new Endereco();
		
		medico = new Medico();
		medico.setUsuario(new Usuario());
		medico.setPessoa(new Pessoa());
		medico.getPessoa().setEnderecos(new ArrayList<Endereco>());
		medico.getPessoa().setTelefones(new ArrayList<Telefone>());
		
		//Teste
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

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public TipoEnderecoEnum[] getTiposEndereco(){
		return TipoEnderecoEnum.values();
	}
	
	public List<Cidade> cidadesAutoComplete(String query){
		List<Cidade> cidadesTemp = new ArrayList<Cidade>();
		for(Cidade cidade: cidades){
			if(Util.removeAcentos(cidade.getNome()).toLowerCase().contains(Util.removeAcentos(query).toLowerCase())){
				cidadesTemp.add(cidade);
			}
		}
		return cidadesTemp;
	}
	
	public List<String> cidadesAutoComplete2(String query){
		return null;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medicoParaInserir) {
		this.medico = medicoParaInserir;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String salvarEndereco(){
		medico.getPessoa().getEnderecos().add(endereco.clonar());
		endereco = new Endereco();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Endereço salvo com sucesso!", ""));
		return "";
	}
	
	public Boolean getRenderListEnderecos(){
		return !medico.getPessoa().getEnderecos().isEmpty();
	}
	
	public String removerEndereco(){
		if(endereco != null){
			medico.getPessoa().getEnderecos().remove(endereco);
			endereco = new Endereco();
		}
		return "";
	}
	
	public String editarEndereco(){
		medico.getPessoa().getEnderecos().remove(endereco);
		return "";
	}
}
