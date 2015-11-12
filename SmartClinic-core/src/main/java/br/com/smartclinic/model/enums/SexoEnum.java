package br.com.smartclinic.model.enums;

public enum SexoEnum {
	MASCULINO("Masculino"), FEMININO("Feminino");
	
	private String nome;
	
	SexoEnum(String nome){
		this.setNome(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
