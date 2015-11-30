package br.com.smartclinic.model.enums;

public enum TipoEnderecoEnum {
	COMERCIAL("Comercial"), RESIDENCIAL("Residencial");
	
	private String nome;
	
	TipoEnderecoEnum(String nome){
		this.setNome(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
