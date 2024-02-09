package it.fides.val_training_spring.models.dto;

public class ScalaDto {

	private Long idScala;

	private String titoloScala;

	private String descrizioneScala;

	private Long paragrafo;
	
	private Long sezioneDto;

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

	public Long getParagrafo() {
		return paragrafo;
	}

	public void setParagrafo(Long paragrafo) {
		this.paragrafo = paragrafo;
	}
	
	

	public Long getSezione() {
		return sezioneDto;
	}

	public void setSezione(Long sezioneDto) {
		this.sezioneDto = sezioneDto;
	}

	@Override
	public String toString() {
		return "ScalaDto [idScala=" + idScala + ", titoloScala=" + titoloScala + ", descrizioneScala="
				+ descrizioneScala + ", paragrafo=" + paragrafo + ", sezione=" + sezioneDto + "]";
	}


}
