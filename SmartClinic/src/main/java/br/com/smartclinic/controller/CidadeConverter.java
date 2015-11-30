package br.com.smartclinic.controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.smartclinic.model.Cidade;
import br.com.smartclinic.service.CidadeService;

@FacesConverter("cidadeConverter")
public class CidadeConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value != null && value.trim().length() > 0){
			return CidadeService.getInstance().consultarPorId(new Long(value));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			return ((Cidade)value).getId().toString();
		}
		return null;
	}

}
