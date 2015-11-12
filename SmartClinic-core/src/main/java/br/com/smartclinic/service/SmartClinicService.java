package br.com.smartclinic.service;

import br.com.smartclinic.RegraNegocioException;
import br.com.smartclinic.bo.MedicoBO;
import br.com.smartclinic.model.Medico;

public class SmartClinicService {

	private static SmartClinicService instance;
	private MedicoBO medicoBO;
	
	private SmartClinicService(){
		this.medicoBO = MedicoBO.getInstance();
	}
	
	public static SmartClinicService getInstance(){
		if(instance == null){
			instance = new SmartClinicService();
		}
		return instance;
	}
	
	public Medico inserirMedico(Medico medico) throws RegraNegocioException{
		return medicoBO.inserir(medico);
	}
	
}
