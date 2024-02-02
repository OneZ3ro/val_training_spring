package it.fides.val_training_spring.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "scala")

public class SezioneEntity {

	@Id
	@Column(name = "id_sezione")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSezione;

	@Column(name = "titolo")
	private String titolo;

	@Column(name = "data_creazione")
	private LocalDateTime dataCreazione;

	@Column(name = "data_modifica")
	private LocalDateTime dataModifica;

	@Column(name = "flg_cancellato")
	private boolean flgCancellato;

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

}
