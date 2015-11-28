package br.com.smartclinic.service;

import java.io.Serializable;

import br.com.smartclinic.RegraNegocioException;
import br.com.smartclinic.bo.UsuarioBO;
import br.com.smartclinic.dao.UsuarioDao;
import br.com.smartclinic.model.Usuario;

public class UsuarioService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static UsuarioService instance;
	private UsuarioDao usuarioDao;
	private UsuarioBO usuarioBO;
	
	private UsuarioService() {
		this.usuarioDao = UsuarioDao.getInstance();
		this.usuarioBO = UsuarioBO.getInstance();
	}
	
	public static UsuarioService getInstance(){
		if(instance == null){
			instance = new UsuarioService();
		}
		return instance;
	}
	
	public Usuario logar(Usuario usuario){
		return usuarioDao.logar(usuario);
	}
	
	public Usuario inserir(Usuario usuario, boolean confirmaTransacao) throws RegraNegocioException{
		return usuarioBO.inserir(usuario, confirmaTransacao);
	}

}
