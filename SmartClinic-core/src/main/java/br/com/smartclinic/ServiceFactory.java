package br.com.smartclinic;

public class ServiceFactory {
	
	private static ServiceFactory instance;
	
	public static ServiceFactory getInstance(){
		if(instance == null){
			instance = new ServiceFactory();
		}
		
		return instance;
	}

}
