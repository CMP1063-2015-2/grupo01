package br.com.smartclinic.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.smartclinic.RegraNegocioException;
import br.com.smartclinic.dao.UsuarioDao;
import br.com.smartclinic.model.Usuario;

public class UsuarioBO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private static UsuarioBO instance;
	private static UsuarioDao usuarioDao;
	
	private UsuarioBO(){
		usuarioDao = UsuarioDao.getInstance();
	}
	
	public static UsuarioBO getInstance(){
		if(instance == null){
			instance = new UsuarioBO();
		}
		return instance;
	}
	
	public Usuario inserir(Usuario usuario, boolean confirmaTransacao) throws RegraNegocioException{
		validaRegrasNegocioInserir(usuario);
		
		usuario = usuarioDao.inserir(usuario, confirmaTransacao);
		
		return usuario;
	}

	/**
	 * Regras de negocio: <br>
	 * 1 - Não podem haver mais de um usuario com o mesmo login
	 * @param usuario
	 * @return
	 * @throws RegraNegocioException 
	 */
	private void validaRegrasNegocioInserir(Usuario usuario) throws RegraNegocioException {
		Usuario usuarioTemp;
		List<Usuario> listTemp;
		List<String> mensagens = new ArrayList<String>();
		
		// Valida Regras de negócio
		//[1]
		usuarioTemp = new Usuario();
		usuarioTemp.setLogin(usuario.getLogin());
		listTemp = usuarioDao.listar(usuarioTemp, true);
		if(listTemp != null && listTemp.size() > 0){
			mensagens.add("Já existe um usuário cadastrado com este login");
		}
		
		if(mensagens.size() > 0){
			throw new RegraNegocioException(mensagens);
		}
	}

}
