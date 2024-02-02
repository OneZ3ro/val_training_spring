package it.fides.val_training_spring.models.dto;

import java.time.LocalDateTime;

public class ValoreDto {

	private Long idValore;

	private String nomeValore;
	
	private String votoValore;

	private ParagrafoDto paragrafo;

	private UtenteDto utente;

	public ValoreDto() {

	}
	
	public ValoreDto(Long idValore, String nomeValore, String votoValore, LocalDateTime dataCreazioneValore,
			LocalDateTime dataModificaValore, Boolean flgCancellatoValore, ParagrafoDto paragrafo,
			UtenteDto utente) {
		this.idValore = idValore;
		this.nomeValore = nomeValore;
		this.votoValore = votoValore;
		this.paragrafo = paragrafo;
		this.utente = utente;
	}

	public Long getIdValore() {
		return idValore;
	}

	public void setIdValore(Long idValore) {
		this.idValore = idValore;
	}

	public String getNomeValore() {
		return nomeValore;
	}

	public void setNomeValore(String nomeValore) {
		this.nomeValore = nomeValore;
	}

	public String getVotoValore() {
		return votoValore;
	}

	public void setVotoValore(String votoValore) {
		this.votoValore = votoValore;
	}

	public ParagrafoDto getParagrafo() {
		return paragrafo;
	}

	public void setParagrafo(ParagrafoDto paragrafo) {
		this.paragrafo = paragrafo;
	}

	public UtenteDto getUtente() {
		return utente;
	}

	public void setUtente(UtenteDto utente) {
		this.utente = utente;
	}

	@Override
	public String toString() {
		return "ValoreDto [idValore=" + idValore + ", nomeValore=" + nomeValore + ", votoValore=" + votoValore
				+ ", paragrafo=" + paragrafo + ", utente=" + utente + "]";
	}
}
