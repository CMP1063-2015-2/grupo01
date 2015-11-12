package br.com.smartclinic.filter;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.com.smartclinic.model.Usuario;

@WebFilter("/public/*")
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
		
		if(usuario == null){
			if(FacesContext.getCurrentInstance() != null){
				FacesContext.getCurrentInstance().getExternalContext().redirect("/index.jsf"); 
			}else{
				request.getRequestDispatcher("/index.jsf").forward(request, response);
			}
			
		}else{
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
