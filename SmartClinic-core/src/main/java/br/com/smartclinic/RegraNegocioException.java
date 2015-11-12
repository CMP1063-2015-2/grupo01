package br.com.smartclinic;

import java.util.ArrayList;
import java.util.List;

public class RegraNegocioException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private List<String> mensagens = new ArrayList<String>();
	
	public RegraNegocioException(String mensagem){
		mensagens.add(mensagem);
	}
	
	public RegraNegocioException(List<String> mensagens){
		this.mensagens = mensagens;
	}
	
	public int qtdMensagens(){
		return mensagens.size();
	}
	
	public List<String> getMensagens(){
		return mensagens;
	}
	
	//teste 2 3 4
}
