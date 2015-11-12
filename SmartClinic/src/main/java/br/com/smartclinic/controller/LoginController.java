package br.com.smartclinic.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.smartclinic.model.Usuario;
import br.com.smartclinic.service.UsuarioService;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable{
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private UsuarioService usuarioService;
	
	@PostConstruct
	public void init(){
		usuario = new Usuario();
		usuarioService = UsuarioService.getInstance();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String logar(){
		Usuario usuarioTemp = usuarioService.logar(usuario);
		
		if(usuarioTemp != null){
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("usuarioLogado", usuarioTemp);
			return "public/main";
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inválidos!", ""));
		return "index";
	}
	
	public void deslogar() throws IOException{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("usuarioLogado", null);
		session.invalidate();
		FacesContext.getCurrentInstance().getExternalContext().redirect("/SmartClinic/index.jsf"); 
	}
}
