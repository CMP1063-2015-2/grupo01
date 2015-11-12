package br.com.smartclinic.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.smartclinic.model.Medico;
import br.com.smartclinic.service.MedicoService;

@ManagedBean
@ViewScoped
public class MedicoController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Medico actionMedico;
	private List<Medico> medicoList;
	private MedicoService medicoService;
	
	@PostConstruct
	public void init(){
		medicoService = MedicoService.getInstance();
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
	

}
