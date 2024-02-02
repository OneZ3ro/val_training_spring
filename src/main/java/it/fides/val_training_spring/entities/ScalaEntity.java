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
@Table(name = "scala")
public class ScalaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_scala")
	private Long idScala;

	@Column(name = "titolo", nullable=false)
	private String titolo;

	@Column(name = "descrizione")
	private String descrizione;

	@Column(name = "data_creazione", nullable=false)
	private LocalDateTime dataCreazione;

	@Column(name = "data_modifica", nullable=false)
	private LocalDateTime dataModifica;

	@Column(name = "flg_cancellato", nullable=false)
	private boolean flgCancellato;

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

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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

	
	public ParagrafoEntity getParagrafo() {
		return paragrafo;
	}

	public void setParagrafo(ParagrafoEntity paragrafo) {
		this.paragrafo = paragrafo;
	}

	@Override
	public String toString() {
		return "ScalaEntity [idScala=" + idScala + ", titolo=" + titolo + ", descrizione=" + descrizione
				+ ", dataCreazione=" + dataCreazione + ", dataModifica=" + dataModifica + ", flgCancellato="
				+ flgCancellato + ", paragrafo=" + paragrafo + "]";
	}

	
}
