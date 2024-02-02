package it.fides.val_training_spring.entities;

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
	@Column(name = "nome")
	private String nomeValore;
	@Column(name = "voto")
	private String votoValore;
	@Column(name = "data_creazione", nullable = false)
	private LocalDateTime dataCreazioneValore;
	@Column(name = "data_modifica", nullable = false)
	private LocalDateTime dataModificaValore;
	@Column(name = "flg_cancellato", nullable = false)
	private Boolean flgCancellatoValore;
	@ManyToOne
	@JoinColumn(name = "id_paragrafo", nullable = false)
	private Long idParagrafoValore;
	@ManyToOne
	@JoinColumn(name = "id_utente", nullable = false)
	private Long idUtenteValore;
	
	public ValoreEntity(Long idValore, String nomeValore, String votoValore, LocalDateTime dataCreazioneValore,
			LocalDateTime dataModificaValore, Boolean flgCancellatoValore, Long idRoleValore, Long idUserValore) {
		this.idValore = idValore;
		this.nomeValore = nomeValore;
		this.votoValore = votoValore;
		this.dataCreazioneValore = dataCreazioneValore;
		this.dataModificaValore = dataModificaValore;
		this.flgCancellatoValore = flgCancellatoValore;
		this.idParagrafoValore = idRoleValore;
		this.idUtenteValore = idUserValore;
	}
	
	public ValoreEntity() {
		
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

	public Long getIdParagrafoValore() {
		return idParagrafoValore;
	}

	public void setIdParagrafoValore(Long idParagrafoValore) {
		this.idParagrafoValore = idParagrafoValore;
	}

	public Long getIdUtenteValore() {
		return idUtenteValore;
	}

	public void setIdUtenteValore(Long idUtenteValore) {
		this.idUtenteValore = idUtenteValore;
	}

	
	
}
