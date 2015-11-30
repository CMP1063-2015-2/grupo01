package br.com.smartclinic.utils;

import java.io.Serializable;
import java.text.Normalizer;

public class Util implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static String removeAcentos(String str){
		return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	

}
