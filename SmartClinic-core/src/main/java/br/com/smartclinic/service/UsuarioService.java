package br.com.smartclinic.service;

import java.io.Serializable;

import br.com.smartclinic.dao.UsuarioDao;
import br.com.smartclinic.model.Usuario;

public class UsuarioService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static UsuarioService instance;
	private UsuarioDao usuarioDao;
	
	private UsuarioService() {
		this.usuarioDao = UsuarioDao.getInstance();
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

}
