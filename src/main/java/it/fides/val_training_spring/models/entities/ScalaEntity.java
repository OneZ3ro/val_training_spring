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
@Table(name = "scala")
public class ScalaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_scala")
	private Long idScala;

	@Column(name = "titolo", nullable=false, length = 64)
	private String titoloScala;

	@Column(name = "descrizione", length = 1000)
	private String descrizioneScala;

	@Column(name = "data_creazione", nullable=false)
	private LocalDateTime dataCreazioneScala;

	@Column(name = "data_modifica", nullable=false)
	private LocalDateTime dataModificaScala;

	@Column(name = "flg_cancellato", nullable=false)
	private boolean flgCancellatoScala;

	@ManyToOne
	@JoinColumn(name = "id_paragrafo")
	private ParagrafoEntity paragrafo;

	// Getter & Setter

	public Long getIdScala() {
		return idScala;
	}

	public void setIdScala(Long idScala) {
		this.idScala = idScala;
	}
	
	public String getTitoloScala() {
		return titoloScala;
	}

	public void setTitoloScala(String titoloScala) {
		this.titoloScala = titoloScala;
	}

	public String getDescrizioneScala() {
		return descrizioneScala;
	}

	public void setDescrizioneScala(String descrizioneScala) {
		this.descrizioneScala = descrizioneScala;
	}

	public LocalDateTime getDataCreazioneScala() {
		return dataCreazioneScala;
	}

	public void setDataCreazioneScala(LocalDateTime dataCreazioneScala) {
		this.dataCreazioneScala = dataCreazioneScala;
	}

	public LocalDateTime getDataModificaScala() {
		return dataModificaScala;
	}

	public void setDataModificaScala(LocalDateTime dataModificaScala) {
		this.dataModificaScala = dataModificaScala;
	}

	public boolean isFlgCancellatoScala() {
		return flgCancellatoScala;
	}

	public void setFlgCancellatoScala(boolean flgCancellatoScala) {
		this.flgCancellatoScala = flgCancellatoScala;
	}

	public ParagrafoEntity getParagrafo() {
		return paragrafo;
	}

	public void setParagrafo(ParagrafoEntity paragrafo) {
		this.paragrafo = paragrafo;
	}

	@Override
	public String toString() {
		return "ScalaEntity [idScala=" + idScala + ", titoloScala=" + titoloScala + ", descrizioneScala="
				+ descrizioneScala + ", dataCreazioneScala=" + dataCreazioneScala + ", dataModificaScala="
				+ dataModificaScala + ", flgCancellatoScala=" + flgCancellatoScala + ", paragrafo=" + paragrafo + "]";
	}	
}
