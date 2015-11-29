package br.com.smartclinic.service;

import java.io.Serializable;
import java.util.List;

import br.com.smartclinic.RegraNegocioException;
import br.com.smartclinic.bo.CidadeBO;
import br.com.smartclinic.dao.CidadeDao;
import br.com.smartclinic.model.Cidade;

public class CidadeService implements Serializable{
	private static final long serialVersionUID = -4992661773986502257L;
	private static CidadeService instance;
	private CidadeBO cidadeBO;
	private CidadeDao cidadeDao;
	
	private CidadeService(){
		cidadeBO = CidadeBO.getInstance();
		cidadeDao = CidadeDao.getInstance();
	}
	
	public static CidadeService getInstance(){
		if(instance == null){
			instance = new CidadeService();
		}
		return instance;
	}
	
	public Cidade inserir(Cidade cidade, boolean confirmaTransacao) throws RegraNegocioException{
		return cidadeBO.inserir(cidade, confirmaTransacao);
	}
	
	public List<Cidade> listar(Cidade cidade, boolean isExato){
		return cidadeDao.listar(cidade, isExato);
	}

}
