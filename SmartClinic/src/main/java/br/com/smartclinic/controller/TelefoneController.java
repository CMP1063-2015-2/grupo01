package br.com.smartclinic.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.smartclinic.model.Telefone;
import br.com.smartclinic.model.enums.TipoTelefoneEnum;

@ManagedBean
@ViewScoped
public class TelefoneController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Telefone> telefones;
	private Telefone telefone;
	
	@PostConstruct
	public void init(){
		telefone = new Telefone();
		telefones = new ArrayList<Telefone>();
	}

	public TipoTelefoneEnum[] getTiposTelefone(){
		return TipoTelefoneEnum.values();
	}
	
	public String salvarTelefone(){
		telefones.add(telefone);
		telefone = new Telefone();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Telefone salvo com sucesso!", ""));
		return "";
	}
	
	public Boolean getRenderListTelefones(){
		return !telefones.isEmpty();
	}
	
	public String removerTelefone(){
		if(telefone != null){
			telefones.remove(telefone);
			telefone = new Telefone();
		}
		return "";
	}
	
	public String editarTelefone(){
		telefones.remove(telefone);
		return "";
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
}
