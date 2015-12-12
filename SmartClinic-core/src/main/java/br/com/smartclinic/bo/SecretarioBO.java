package br.com.smartclinic.bo;

import java.io.Serializable;

import br.com.smartclinic.RegraNegocioException;
import br.com.smartclinic.dao.SecretarioDao;
import br.com.smartclinic.model.Secretario;

public class SecretarioBO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static SecretarioBO instance;
	private SecretarioDao secretarioDao;
	
	private SecretarioBO(){
		this.secretarioDao = SecretarioDao.getInstance();
	}
	
	public static SecretarioBO getInstance(){
		if(instance == null){
			instance = new SecretarioBO();
		}
		return instance;
	}
	
	public Secretario inserir(Secretario secretario, boolean confirmaTransacao) throws RegraNegocioException{
		secretario = secretarioDao.inserir(secretario, confirmaTransacao);
		
		return secretario;
	}
}
