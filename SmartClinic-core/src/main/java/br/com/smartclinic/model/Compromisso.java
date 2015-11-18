package br.com.smartclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.smartclinic.model.enums.TipoCompromissoEnum;

@Entity(name = "COMPROMISSO")
public class Compromisso implements TransferEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_compromisso")
	private Long id;
	
	@Column(nullable = false)
	private String titulo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicial", nullable = false)
	private Date dataInicial;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_final", nullable = false)
	private Date dataFinal;
	
	@Column(nullable = false, length = 100)
	private String descricao;
	
	@Enumerated
	@Column(name = "tipo_compromisso", nullable = false)
	private TipoCompromissoEnum tipo;
	
	@OneToOne
	@JoinColumn(name = "id_consulta", nullable = true)
	private Consulta consulta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_agenda", nullable = false)
	private Agenda agenda;
	
	@Override
	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoCompromissoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoCompromissoEnum tipo) {
		this.tipo = tipo;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
