package br.com.smartclinic.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.smartclinic.model.Usuario;

public class UsuarioDao extends ParentDao {
	
	private static final long serialVersionUID = 1L;
	
	private static UsuarioDao instance;
	
	public static UsuarioDao getInstance(){
		if(instance == null){
			instance = new UsuarioDao();
		}
		return instance;
	}
	
	public Usuario inserir(Usuario usuario, boolean confirmaTransacao){
		
		usuario = super.incluir(usuario);
		
		if(confirmaTransacao){
			super.confirmaTransacao();
		}
		
		return usuario;
	}
	
	public Usuario alterar(Usuario usuario, boolean confirmaTranascao){
		
		usuario = super.alterar(usuario);
		
		if(confirmaTranascao){
			super.confirmaTransacao();
		}
		
		return usuario;
	}
	
	public void excluir(Usuario usuario, boolean confirmaTransacao){
		
		super.excluir(usuario);
		
		if(confirmaTransacao){
			super.confirmaTransacao();
		}
	}
	
	public Usuario consultarPorId(Long id){
		Usuario usuario = super.consultarPorId(Usuario.class, id);
		return usuario;
	}
	
	public Usuario logar(Usuario usuario){
		StringBuilder hql = new StringBuilder();
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		hql.append("select bean from ").append(Usuario.class.getName()).append(" bean ");
		hql.append(" where bean.login = :login ");
		hql.append(" and bean.senha = :senha ");
		
		parametros.put("login", usuario.getLogin());
		parametros.put("senha", usuario.getSenha());
		
		return (Usuario) super.uniqueResult(hql, parametros);
	}
	
	public List<Usuario> listar(Usuario usuario, boolean isExato){
		StringBuilder hql = new StringBuilder();
		Map<String, Object> parametros = new HashMap<String, Object>();
		
		hql.append(" select bean from ").append(Usuario.class.getName()).append(" bean ");
		hql.append(" where 1=1 ");
		
		if(usuario.getLogin() != null && !usuario.getLogin().equals("")){
			if(isExato){
				hql.append(" and LOWER(bean.login) = LOWER(:login) ");
				parametros.put("login", usuario.getLogin());
			}else{
				hql.append(" and LOWER(bean.login) like LOWER(:login) ");
				parametros.put("login", "%" + usuario.getLogin() + "%");
			}
		}
		
		if(usuario.getTipoUsuario() != null){
			hql.append(" and bean.tipoUsuario = :tipoUsuario ");
			parametros.put("tipoUsuario", usuario.getTipoUsuario());
		}
		
		return super.listar(Usuario.class, hql, parametros);
	}
}
