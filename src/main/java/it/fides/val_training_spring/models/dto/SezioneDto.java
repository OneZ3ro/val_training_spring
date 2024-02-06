package it.fides.val_training_spring.models.dto;

public class SezioneDto {

	private Long idSezione;

	private String titoloSezione;
	
	public SezioneDto() {}

	// Getter & Setter
	
	public Long getIdSezione() {
		return idSezione;
	}

	public void setIdSezione(Long idSezione) {
		this.idSezione = idSezione;
	}

	public String getTitoloSezione() {
		return titoloSezione;
	}

	public void setTitoloSezione(String titoloSezione) {
		this.titoloSezione = titoloSezione;
	}

	@Override
	public String toString() {
		return "SezioneDto [idSezione=" + idSezione + ", titoloSezione=" + titoloSezione + "]";
	}
}
