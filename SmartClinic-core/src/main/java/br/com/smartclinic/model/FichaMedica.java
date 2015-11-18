package br.com.smartclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "FICHA_MEDICA")
public class FichaMedica implements TransferEntity{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_ficha_medica")
	private Long id;
	
	@Column(name = "principal_queixa")
	private String principalQueixa;
	
	@Column(name = "antecedentes_medicos")
	private String antecedentesMedicos;
	
	@Column(name = "historia_doenca_atual")
	private String historiaDoencaAtual;
	
	@Column(name = "hipotese_diagnostico")
	private String hipoteseDiagnostico;
	
	@Column(name = "medicacao_prescrita")
	private String medicacaoPrescrita;
	
	@Column(name = "exames")
	private String exames;
	
	@Column(name = "exames_complementares")
	private String examesComplementares;
	
	@OneToOne
	@JoinColumn(name = "id_paciente", nullable = false)
	private Paciente paciente;

	@Override
	public Long getId() {
		return this.id;
	}

	public String getPrincipalQueixa() {
		return principalQueixa;
	}

	public void setPrincipalQueixa(String principalQueixa) {
		this.principalQueixa = principalQueixa;
	}

	public String getAntecedentesMedicos() {
		return antecedentesMedicos;
	}

	public void setAntecedentesMedicos(String antecedentesMedicos) {
		this.antecedentesMedicos = antecedentesMedicos;
	}

	public String getHistoriaDoencaAtual() {
		return historiaDoencaAtual;
	}

	public void setHistoriaDoencaAtual(String historiaDoencaAtual) {
		this.historiaDoencaAtual = historiaDoencaAtual;
	}

	public String getHipoteseDiagnostico() {
		return hipoteseDiagnostico;
	}

	public void setHipoteseDiagnostico(String hipoteseDiagnostico) {
		this.hipoteseDiagnostico = hipoteseDiagnostico;
	}

	public String getMedicacaoPrescrita() {
		return medicacaoPrescrita;
	}

	public void setMedicacaoPrescrita(String medicacaoPrescrita) {
		this.medicacaoPrescrita = medicacaoPrescrita;
	}

	public String getExames() {
		return exames;
	}

	public void setExames(String exames) {
		this.exames = exames;
	}

	public String getExamesComplementares() {
		return examesComplementares;
	}

	public void setExamesComplementares(String examesComplementares) {
		this.examesComplementares = examesComplementares;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
