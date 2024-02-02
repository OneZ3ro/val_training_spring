package it.fides.val_training_spring.models.dto;

public class SezioneDto {

	private Long idSezione;

	private String titolo;
	
	public SezioneDto() {}

	// Getter & Setter
	
	public Long getIdSezione() {
		return idSezione;
	}

	public void setIdSezione(Long idSezione) {
		this.idSezione = idSezione;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	@Override
	public String toString() {
		return "SezioneDto [idSezione=" + idSezione + ", titolo=" + titolo + "]";
	}
}
