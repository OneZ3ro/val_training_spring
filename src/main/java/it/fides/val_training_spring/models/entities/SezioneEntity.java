package it.fides.val_training_spring.models.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sezione")
public class SezioneEntity {

	@Id
	@Column(name = "id_sezione")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSezione;

	@Column(name = "titolo", nullable=false, length = 64)
	private String titoloSezione;

	@Column(name = "data_creazione", nullable=false)
	private LocalDateTime dataCreazioneSezione;

	@Column(name = "data_modifica", nullable=false)
	private LocalDateTime dataModificaSezione;

	@Column(name = "flg_cancellato", nullable=false)
	private boolean flgCancellatoSezione;

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

	public LocalDateTime getDataCreazioneSezione() {
		return dataCreazioneSezione;
	}

	public void setDataCreazioneSezione(LocalDateTime dataCreazioneSezione) {
		this.dataCreazioneSezione = dataCreazioneSezione;
	}

	public LocalDateTime getDataModificaSezione() {
		return dataModificaSezione;
	}

	public void setDataModificaSezione(LocalDateTime dataModificaSezione) {
		this.dataModificaSezione = dataModificaSezione;
	}

	public boolean isFlgCancellatoSezione() {
		return flgCancellatoSezione;
	}

	public void setFlgCancellatoSezione(boolean flgCancellatoSezione) {
		this.flgCancellatoSezione = flgCancellatoSezione;
	}

	@Override
	public String toString() {
		return "SezioneEntity [idSezione=" + idSezione + ", titoloSezione=" + titoloSezione + ", dataCreazioneSezione="
				+ dataCreazioneSezione + ", dataModificaSezione=" + dataModificaSezione + ", flgCancellatoSezione="
				+ flgCancellatoSezione + "]";
	}
}
