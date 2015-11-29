package br.com.smartclinic.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.smartclinic.RegraNegocioException;
import br.com.smartclinic.dao.CidadeDao;
import br.com.smartclinic.model.Cidade;

public class CidadeBO implements Serializable{
	
	private static final long serialVersionUID = 6873918009388753159L;
	private static CidadeBO instance;
	private CidadeDao cidadeDao;
	
	private CidadeBO(){
		cidadeDao = CidadeDao.getInstance();
	}
	
	public static CidadeBO getInstance(){
		if(instance == null){
			instance = new CidadeBO();
		}
		return instance;
	}
	
	public Cidade inserir(Cidade cidade, boolean confirmaTransacao) throws RegraNegocioException{
		validaRegrasNegocioInserir(cidade);
		
		cidade = cidadeDao.inserir(cidade, confirmaTransacao);
		
		return cidade;
	}

	/**
	 * Regras de negocio: <br>
	 * 1 - Não podem haver cidades com nome igual no mesmo estado<br>
	 * @param cidade
	 * @return
	 * @throws RegraNegocioException 
	 */
	private void validaRegrasNegocioInserir(Cidade cidade)
			throws RegraNegocioException {
		Cidade cidadeTemp;
		List<Cidade> listCidadesTemp;
		List<String> mensagens = new ArrayList<String>();
		
		// Valida Regras de negócio
		//[1]
		cidadeTemp = new Cidade();
		cidadeTemp.setEstado(cidade.getEstado());
		cidadeTemp.setNome(cidade.getNome());
		listCidadesTemp = cidadeDao.listar(cidadeTemp, true);
		if(listCidadesTemp != null && listCidadesTemp.size() > 0){
			mensagens.add("Já existe uma cidade com este mesmo nome e estado!");
		}
		
		if(mensagens.size() > 0){
			throw new RegraNegocioException(mensagens);
		}
	}

}
