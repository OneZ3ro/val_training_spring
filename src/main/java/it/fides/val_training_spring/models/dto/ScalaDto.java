package it.fides.val_training_spring.models.dto;

public class ScalaDto {

	private Long idScala;

	private String titoloScala;

	private String descrizioneScala;

	private ParagrafoDto paragrafo;

	// Getter & Setter

	public Long getIdScala() {
		return idScala;
	}

	public void setIdScala(Long idScala) {
		this.idScala = idScala;
	}

	public String getTitoloScala() {
		return titoloScala;
	}

	public void setTitoloScala(String titoloScala) {
		this.titoloScala = titoloScala;
	}

	public String getDescrizioneScala() {
		return descrizioneScala;
	}

	public void setDescrizioneScala(String descrizioneScala) {
		this.descrizioneScala = descrizioneScala;
	}

	public ParagrafoDto getParagrafo() {
		return paragrafo;
	}

	public void setParagrafo(ParagrafoDto paragrafo) {
		this.paragrafo = paragrafo;
	}

	@Override
	public String toString() {
		return "ScalaDto [idScala=" + idScala + ", titoloScala=" + titoloScala + ", descrizioneScala="
				+ descrizioneScala + ", paragrafoScala=" + paragrafo + "]";
	}
}
