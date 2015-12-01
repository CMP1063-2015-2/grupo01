package br.com.smartclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.smartclinic.model.enums.TipoTelefoneEnum;

@Entity(name = "TELEFONE")
public class Telefone implements TransferEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_telefone")
	private Long id;
	
	@Column(name = "numero", nullable = false)
	private String numero;
	
	@Enumerated
	@Column(name = "tipo_telefone")
	private TipoTelefoneEnum tipo;
	
	@Override
	public Long getId() {
		return this.id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoTelefoneEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoTelefoneEnum tipo) {
		this.tipo = tipo;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Telefone){
			return ((Telefone)obj).getNumero().equalsIgnoreCase(this.numero) && ((Telefone)obj).getTipo().equals(this.tipo);
		}
		return super.equals(obj);
	}
}
