package it.fides.val_training_spring.entities;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "utente")
public class UtenteEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_utente")
	private Long idUtente;
	
	@Column(name = "nome", nullable=false)
	private String nomeUtente;
	
	@Column(name = "cognome", nullable=false)
	private String cognomeUtente;
	
	@Column(name = "email", nullable=false, unique = true)
	private String emailUtente;
	
	@Column(name = "password", nullable=false)
	private String passwordUtente;
	
	@Column(name = "informazioni_generali", nullable=false)
	private String informazioniGeneraliUtente;
	
	@Column(name = "data_creazione", nullable=false)
	private LocalDateTime dataCreazioneUtente;
	
	@Column(name = "data_modifica", nullable=false)
	private LocalDateTime dataModificaUtente;
	
	@Column(name = "flg_cancellato", nullable=false)
	private boolean flgCancellato;
	
    @ManyToOne
    @JoinColumn(name = "id_ruolo")
    private RuoloEntity ruolo;
    
	@ManyToMany
    @JoinTable(
            name = "utente_gruppo",
            joinColumns = @JoinColumn(name = "id_utente"),
            inverseJoinColumns = @JoinColumn(name = "id_gruppo"))
    private List<GruppoEntity> gruppi;
    
    public UtenteEntity() {}

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
	public LocalDateTime getDataCreazioneUtente() {
		return dataCreazioneUtente;
	}
	public void setDataCreazioneUtente(LocalDateTime dataCreazioneUtente) {
		this.dataCreazioneUtente = dataCreazioneUtente;
	}
	public LocalDateTime getDataModificaUtente() {
		return dataModificaUtente;
	}
	public void setDataModificaUtente(LocalDateTime dataModificaUtente) {
		this.dataModificaUtente = dataModificaUtente;
	}
	public boolean isFlgCancellato() {
		return flgCancellato;
	}
	public void setFlgCancellato(boolean flgCancellato) {
		this.flgCancellato = flgCancellato;
	}
	public RuoloEntity getRuolo() {
		return ruolo;
	}
	public void setRuolo(RuoloEntity ruolo) {
		this.ruolo = ruolo;
	}
	public List<GruppoEntity> getGruppi() {
		return gruppi;
	}
	public void setGruppi(List<GruppoEntity> gruppi) {
		this.gruppi = gruppi;
	}

	@Override
	public String toString() {
		return "UtenteEntity [idUtente=" + idUtente + ", nomeUtente=" + nomeUtente + ", cognomeUtente=" + cognomeUtente
				+ ", emailUtente=" + emailUtente + ", passwordUtente=" + passwordUtente
				+ ", informazioniGeneraliUtente=" + informazioniGeneraliUtente + ", dataCreazioneUtente="
				+ dataCreazioneUtente + ", dataModificaUtente=" + dataModificaUtente + ", flgCancellato="
				+ flgCancellato + ", ruolo=" + ruolo + ", gruppi=" + gruppi + "]";
	}
}
