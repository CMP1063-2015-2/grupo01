package br.com.smartclinic.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.smartclinic.RegraNegocioException;
import br.com.smartclinic.dao.PessoaDao;
import br.com.smartclinic.model.Pessoa;

public class PessoaBO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static PessoaBO instance;
	private static PessoaDao pessoaDao;
	
	private PessoaBO(){
		pessoaDao = PessoaDao.getInstance();
	}
	
	public static PessoaBO getInstance(){
		if(instance == null){
			instance = new PessoaBO();
		}
		return instance;
	}
	
	/**
	 * Regras de negocio: <br>
	 * 1 - Não podem haver pessoas com CPF repetido<br>
	 * 2 - Não podem haver pessoas com RG repetido<br>
	 * @param pessoa
	 * @return
	 * @throws RegraNegocioException 
	 */
	public void validaRegrasNegocioInserirPessoa(Pessoa pessoa) throws RegraNegocioException {
		Pessoa pessoaTemp;
		List<Pessoa> listTemp;
		List<String> mensagens = new ArrayList<String>();
		
		// Valida Regras de negócio
		//[1]
		pessoaTemp = new Pessoa();
		pessoaTemp.setCpf(pessoa.getCpf());
		listTemp = pessoaDao.listar(pessoaTemp);
		if(listTemp != null && listTemp.size() > 0){
			mensagens.add("Já existe uma pessoa cadastrada com este CPF");
		}
		
		pessoaTemp = new Pessoa();
		pessoaTemp.setRg(pessoa.getRg());
		listTemp = pessoaDao.listar(pessoaTemp);
		if(listTemp != null && listTemp.size() > 0){
			mensagens.add("Já existe uma pessoa cadastrada com este RG");
		}
		
		if(mensagens.size() > 0){
			throw new RegraNegocioException(mensagens);
		}
	}

}
