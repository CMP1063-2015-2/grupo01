package br.com.smartclinic.model.enums;

public enum TipoTelefoneEnum {
	FIXO("Fixo"), CELULAR("Celular");
	
	String nome;
	
	private TipoTelefoneEnum(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
