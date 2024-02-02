package it.fides.val_training_spring.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "gruppo")
public class GruppoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_gruppo")
	private Long idGruppo;
	
	@Column(name = "nome")
	private String nomeGruppo;
	
	@Column(name = "data_creazione")
	private LocalDateTime dataCreazione;
	
	@Column(name = "data_modifica")
	private LocalDateTime dataModifica;
	
	@Column(name = "flg_cancellato")
	private boolean flgCancellato;
	
	@OneToOne
	@JoinColumn(name = "responsabile")
	private UtenteEntity responsabile;

	public Long getIdGruppo() {
		return idGruppo;
	}

	public void setIdGruppo(Long idGruppo) {
		this.idGruppo = idGruppo;
	}

	public String getNomeGruppo() {
		return nomeGruppo;
	}

	public void setNomeGruppo(String nomeGruppo) {
		this.nomeGruppo = nomeGruppo;
	}

	public LocalDateTime getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(LocalDateTime dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public LocalDateTime getDataModifica() {
		return dataModifica;
	}

	public void setDataModifica(LocalDateTime dataModifica) {
		this.dataModifica = dataModifica;
	}

	public boolean isFlgCancellato() {
		return flgCancellato;
	}

	public void setFlgCancellato(boolean flgCancellato) {
		this.flgCancellato = flgCancellato;
	}

	public UtenteEntity getIdResponsabile() {
		return responsabile;
	}

	public void setIdResponsabile(UtenteEntity idResponsabile) {
		this.responsabile = idResponsabile;
	}

	@Override
	public String toString() {
		return "GruppoEntity [idGruppo=" + idGruppo + ", nomeGruppo=" + nomeGruppo + ", dataCreazione=" + dataCreazione
				+ ", dataModifica=" + dataModifica + ", flgCancellato=" + flgCancellato + ", idResponsabile="
				+ responsabile + "]";
	}
	
	
}
