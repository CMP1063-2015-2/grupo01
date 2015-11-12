package br.com.smartclinic.service;

import java.util.List;

import br.com.smartclinic.dao.MedicoDao;
import br.com.smartclinic.model.Medico;

public class MedicoService {
	private static MedicoService instance;
	private MedicoDao medicoDao;
	
	private MedicoService(){
		medicoDao = MedicoDao.getInstance();
	}
	
	public static MedicoService getInstance(){
		if(instance == null){
			instance = new MedicoService();
		}
		return instance;
	}
	
	public List<Medico> listar(Medico medico){
		return medicoDao.listar(medico, false);
	}

}
