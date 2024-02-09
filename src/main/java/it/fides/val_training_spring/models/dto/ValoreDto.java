package it.fides.val_training_spring.models.dto;

import java.time.LocalDateTime;

public class ValoreDto {

	private Long idValore;

	private String nomeValore;
	
	private String votoValore;

	private Long paragrafo;

	private Long utente;

	public ValoreDto() {

	}
	
	public ValoreDto(Long idValore, String nomeValore, String votoValore, LocalDateTime dataCreazioneValore,
			LocalDateTime dataModificaValore, Boolean flgCancellatoValore, Long paragrafo,
			Long utente) {
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

	public Long getParagrafo() {
		return paragrafo;
	}

	public void setParagrafo(long paragrafo) {
		this.paragrafo = paragrafo;
	}

	public Long getUtente() {
		return utente;
	}

	public void setUtente(Long utente) {
		this.utente = utente;
	}

	@Override
	public String toString() {
		return "ValoreDto [idValore=" + idValore + ", nomeValore=" + nomeValore + ", votoValore=" + votoValore
				+ ", paragrafo=" + paragrafo + ", utente=" + utente + "]";
	}
}
