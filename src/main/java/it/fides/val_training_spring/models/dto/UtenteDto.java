package it.fides.val_training_spring.models.dto;

import java.util.List;

public class UtenteDto {
	
	private Long idUtente;
	private String nomeUtente;
	private String cognomeUtente;
	private String emailUtente;
	private String passwordUtente;
	private String informazioniGeneraliUtente;
    private RuoloDto ruolo;
    private List<GruppoDto> gruppi;
    
    public UtenteDto() {}
    
    public Long getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}
	public String getNomeUtente() {
		return nomeUtente;
	}
	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}
	public String getCognomeUtente() {
		return cognomeUtente;
	}
	public void setCognomeUtente(String cognomeUtente) {
		this.cognomeUtente = cognomeUtente;
	}
	public String getEmailUtente() {
		return emailUtente;
	}
	public void setEmailUtente(String emailUtente) {
		this.emailUtente = emailUtente;
	}
	public String getPasswordUtente() {
		return passwordUtente;
	}
	public void setPasswordUtente(String passwordUtente) {
		this.passwordUtente = passwordUtente;
	}
	public String getInformazioniGeneraliUtente() {
		return informazioniGeneraliUtente;
	}
	public void setInformazioniGeneraliUtente(String informazioniGeneraliUtente) {
		this.informazioniGeneraliUtente = informazioniGeneraliUtente;
	}
	public RuoloDto getRuolo() {
		return ruolo;
	}
	public void setRuolo(RuoloDto ruolo) {
		this.ruolo = ruolo;
	}
	public List<GruppoDto> getGruppi() {
		return gruppi;
	}
	public void setGruppi(List<GruppoDto> gruppi) {
		this.gruppi = gruppi;
	}

	@Override
	public String toString() {
		return "UtenteDto [idUtente=" + idUtente + ", nomeUtente=" + nomeUtente + ", cognomeUtente=" + cognomeUtente
				+ ", emailUtente=" + emailUtente + ", passwordUtente=" + passwordUtente
				+ ", informazioniGeneraliUtente=" + informazioniGeneraliUtente + ", ruolo=" + ruolo + ", gruppi="
				+ gruppi + "]";
	}
}
