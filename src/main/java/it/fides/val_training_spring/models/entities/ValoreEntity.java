package it.fides.val_training_spring.models.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "valore")
public class ValoreEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_valore", nullable = false)
	private Long idValore;
	
	@Column(name = "nome", length = 32)
	private String nomeValore;
	
	@Column(name = "voto", length = 32)
	private String votoValore;
	
	@Column(name = "data_creazione", nullable = false)
	private LocalDateTime dataCreazioneValore;
	
	@Column(name = "data_modifica", nullable = false)
	private LocalDateTime dataModificaValore;
	
	@Column(name = "flg_cancellato", nullable = false)
	private Boolean flgCancellatoValore;
	
	@ManyToOne
	@JoinColumn(name = "id_paragrafo", nullable = false)
	private ParagrafoEntity paragrafo;
	
	@ManyToOne
	@JoinColumn(name = "id_utente", nullable = false)
	private UtenteEntity utente;

	public ValoreEntity() {

	}
	
	public ValoreEntity(Long idValore, String nomeValore, String votoValore, LocalDateTime dataCreazioneValore,
			LocalDateTime dataModificaValore, Boolean flgCancellatoValore, ParagrafoEntity paragrafo,
			UtenteEntity utente) {
		this.idValore = idValore;
		this.nomeValore = nomeValore;
		this.votoValore = votoValore;
		this.dataCreazioneValore = dataCreazioneValore;
		this.dataModificaValore = dataModificaValore;
		this.flgCancellatoValore = flgCancellatoValore;
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

	public LocalDateTime getDataCreazioneValore() {
		return dataCreazioneValore;
	}

	public void setDataCreazioneValore(LocalDateTime dataCreazioneValore) {
		this.dataCreazioneValore = dataCreazioneValore;
	}

	public LocalDateTime getDataModificaValore() {
		return dataModificaValore;
	}

	public void setDataModificaValore(LocalDateTime dataModificaValore) {
		this.dataModificaValore = dataModificaValore;
	}

	public Boolean getFlgCancellatoValore() {
		return flgCancellatoValore;
	}

	public void setFlgCancellatoValore(Boolean flgCancellatoValore) {
		this.flgCancellatoValore = flgCancellatoValore;
	}

	public ParagrafoEntity getParagrafo() {
		return paragrafo;
	}

	public void setParagrafo(ParagrafoEntity paragrafo) {
		this.paragrafo = paragrafo;
	}

	public UtenteEntity getUtente() {
		return utente;
	}

	public void setUtente(UtenteEntity utente) {
		this.utente = utente;
	}

	@Override
	public String toString() {
		return "ValoreEntity [idValore=" + idValore + ", nomeValore=" + nomeValore + ", votoValore=" + votoValore
				+ ", dataCreazioneValore=" + dataCreazioneValore + ", dataModificaValore=" + dataModificaValore
				+ ", flgCancellatoValore=" + flgCancellatoValore + ", paragrafo=" + paragrafo + ", utente=" + utente
				+ "]";
	}

}
