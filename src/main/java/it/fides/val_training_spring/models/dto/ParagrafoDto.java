package it.fides.val_training_spring.models.dto;

public class ParagrafoDto {
	
	private Long idParagrafo;
	
	private String titoloParagrafo;
	
	private String descrizioneParagrafo;
	
	private UtenteDto utente;
	
	private SezioneDto sezione;
	
	//GETTER E SETTER

	public Long getIdParagrafo() {
		return idParagrafo;
	}

	public void setIdParagrafo(Long idParagrafo) {
		this.idParagrafo = idParagrafo;
	}

	public String getTitoloParagrafo() {
		return titoloParagrafo;
	}

	public void setTitoloParagrafo(String titoloParagrafo) {
		this.titoloParagrafo = titoloParagrafo;
	}

	public String getDescrizioneParagrafo() {
		return descrizioneParagrafo;
	}

	public void setDescrizioneParagrafo(String descrizioneParagrafo) {
		this.descrizioneParagrafo = descrizioneParagrafo;
	}

	public UtenteDto getUtente() {
		return utente;
	}

	public void setUtente(UtenteDto utente) {
		this.utente = utente;
	}

	public SezioneDto getSezione() {
		return sezione;
	}

	public void setSezione(SezioneDto sezione) {
		this.sezione = sezione;
	}

	@Override
	public String toString() {
		return "ParagrafoDto [idParagrafo=" + idParagrafo + ", titoloParagrafo=" + titoloParagrafo
				+ ", descrizioneParagrafo=" + descrizioneParagrafo + ", utente=" + utente + ", sezione=" + sezione
				+ "]";
	}
}
