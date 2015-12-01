package br.com.smartclinic.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.smartclinic.model.Cidade;
import br.com.smartclinic.model.Endereco;
import br.com.smartclinic.model.enums.TipoEnderecoEnum;
import br.com.smartclinic.service.CidadeService;
import br.com.smartclinic.utils.Util;

@ManagedBean
@ViewScoped
public class EnderecoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Endereco> enderecos;
	private List<Cidade> cidades;
	private Endereco endereco;
	
	@PostConstruct
	public void init(){
		cidades = CidadeService.getInstance().listar(new Cidade(), true);
		endereco = new Endereco();
		enderecos = new ArrayList<Endereco>();
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
	public TipoEnderecoEnum[] getTiposEndereco(){
		return TipoEnderecoEnum.values();
	}
	
	public List<Cidade> cidadesAutoComplete(String query){
		List<Cidade> cidadesTemp = new ArrayList<Cidade>();
		for(Cidade cidade: cidades){
			if(Util.removeAcentos(cidade.getNome()).toLowerCase().contains(Util.removeAcentos(query).toLowerCase())){
				cidadesTemp.add(cidade);
			}
		}
		return cidadesTemp;
	}
	
	public List<String> cidadesAutoComplete2(String query){
		return null;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String salvarEndereco(){
		enderecos.add(endereco.clonar());
		endereco = new Endereco();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Endereço salvo com sucesso!", ""));
		return "";
	}
	
	public Boolean getRenderListEnderecos(){
		return !enderecos.isEmpty();
	}
	
	public String removerEndereco(){
		if(endereco != null){
			enderecos.remove(endereco);
			endereco = new Endereco();
		}
		return "";
	}
	
	public String editarEndereco(){
		enderecos.remove(endereco);
		return "";
	}
}
