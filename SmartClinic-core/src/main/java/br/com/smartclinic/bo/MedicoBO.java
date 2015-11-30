package br.com.smartclinic.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.smartclinic.RegraNegocioException;
import br.com.smartclinic.dao.MedicoDao;
import br.com.smartclinic.model.Medico;

public class MedicoBO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static MedicoBO instance;
	private MedicoDao medicoDao;
	
	private MedicoBO(){
		this.medicoDao = MedicoDao.getInstance();
	}
	
	public static MedicoBO getInstance(){
		if(instance == null){
			instance = new MedicoBO();
		}
		return instance;
	}
	
	public Medico inserir(Medico medico, boolean confirmaTransacao) throws RegraNegocioException{
		validaRegrasNegocioMedicoInserir(medico);
		
		medico = medicoDao.inserir(medico, confirmaTransacao);
		
		return medico;
	}

	/**
	 * Regras de negocio: <br>
	 * 1 - Não podem haver medicos com o CRM repetido<br>
	 * @param medico
	 * @return
	 * @throws RegraNegocioException 
	 */
	private void validaRegrasNegocioMedicoInserir(Medico medico)
			throws RegraNegocioException {
		Medico medicoTemp;
		List<Medico> listMedicosTemp;
		List<String> mensagens = new ArrayList<String>();
		
		// Valida Regras de negócio
		//[1]
		medicoTemp = new Medico();
		medicoTemp.setCrm(medico.getCrm());
		listMedicosTemp = medicoDao.listar(medicoTemp, true);
		if(listMedicosTemp != null && listMedicosTemp.size() > 0){
			mensagens.add("Já existe um médico cadastrado com este CRM");
		}
		
		if(mensagens.size() > 0){
			throw new RegraNegocioException(mensagens);
		}
	}

}
