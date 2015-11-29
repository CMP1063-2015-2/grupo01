package br.com.smartclinic.dataGenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.com.smartclinic.RegraNegocioException;
import br.com.smartclinic.model.Cidade;
import br.com.smartclinic.service.CidadeService;

public class SmartClinicDataGenerator {
	
	public static void incluirDadosSmartClinic(String path){
		try {
			if(CidadeService.getInstance().listar(new Cidade(), true).size() < 5560){
				incluirCidades(path);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void incluirCidades(String path) throws FileNotFoundException{
		
		JSONParser parser = new JSONParser();
		Cidade cidade;
		
		try {
			JSONObject json = (JSONObject) parser.parse(new InputStreamReader(new FileInputStream(path + "/cidades.json"), "UTF-8"));
			JSONArray jsonArray = (JSONArray) json.get("estados");
			
			for(int i = 0; i < jsonArray.size(); i++){
				JSONObject jsonTemp = (JSONObject) jsonArray.get(i);
				JSONArray jsonArrayTemp = (JSONArray) jsonTemp.get("cidades");
				
				for(int j = 0; j < jsonArrayTemp.size(); j++){
					cidade = new Cidade();
					cidade.setEstado((String) jsonTemp.get("sigla"));
					cidade.setNome((String) jsonArrayTemp.get(j));
					
					try {
						boolean confirmaTransacao = j == jsonArrayTemp.size() - 1;
						CidadeService.getInstance().inserir(cidade, confirmaTransacao);
					} catch (RegraNegocioException e) {
						System.out.println(e.getMensagens());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
