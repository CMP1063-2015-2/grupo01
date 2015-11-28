package br.com.smartclinic.service;

import java.util.ArrayList;
import java.util.List;

import br.com.smartclinic.RegraNegocioException;
import br.com.smartclinic.bo.MedicoBO;
import br.com.smartclinic.dao.MedicoDao;
import br.com.smartclinic.model.Medico;

public class MedicoService {
	private static MedicoService instance;
	private MedicoDao medicoDao;
	private MedicoBO medicoBO;
	private UsuarioService usuarioService;
	
	private MedicoService(){
		medicoDao = MedicoDao.getInstance();
		usuarioService = UsuarioService.getInstance();
		medicoBO = MedicoBO.getInstance();
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
	
	public Medico inserir(Medico medico, boolean confirmaTransacao) throws RegraNegocioException{
		List<String> mensagens = new ArrayList<String>();
		
		try {
			usuarioService.inserir(medico.getUsuario(), false);
		} catch (RegraNegocioException e) {
			mensagens.addAll(e.getMensagens());
		}
		
		try{
			medico = medicoBO.inserir(medico, confirmaTransacao);
		}catch(RegraNegocioException e){
			mensagens.addAll(e.getMensagens());
		}
		
		if(mensagens.size() > 0){
			throw new RegraNegocioException(mensagens);
		}
		
		return medico;
	}

}
