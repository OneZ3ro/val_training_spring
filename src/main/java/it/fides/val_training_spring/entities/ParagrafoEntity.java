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
@Table(name="paragrafo")
public class ParagrafoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idParagrafo;
	
	@Column(name="titolo", nullable=false, length=64)
	private String titoloParagrafo;
	
	@Column(name="descrizione", nullable=true, length=1000)
	private String descrizioneParagrafo;
	
	@Column(name="data_creazione", nullable=false)
	private LocalDateTime dataCreazioneParagrafo;
	
	@Column(name="data_modifica", nullable=false)
	private LocalDateTime dataModificaParagrafo;
	
	@Column(name="flg_cancellato", nullable=false)
	private Boolean flgCancellatoParagrafo;
	
	@ManyToOne
	@JoinColumn(name="utente")
	private UtenteEntity utenteParagrafo;
	
	@ManyToOne
	@JoinColumn(name="sezione")
	private SezioneEntity sezioneParagrafo;
	
	//GETTER E SETTER

	public Long getIdParagrafo() {
		return idParagrafo;
	}

	public void setIdParagrafo(Long idParagrafo) {
		this.idParagrafo = idParagrafo;
	}

	public String getTitoloParagrafo() {
		return titoloParagrafo;
	}

	public void setTitoloParagrafo(String titoloParagrafo) {
		this.titoloParagrafo = titoloParagrafo;
	}

	public String getDescrizioneParagrafo() {
		return descrizioneParagrafo;
	}

	public void setDescrizioneParagrafo(String descrizioneParagrafo) {
		this.descrizioneParagrafo = descrizioneParagrafo;
	}

	public LocalDateTime getDataCreazioneParagrafo() {
		return dataCreazioneParagrafo;
	}

	public void setDataCreazioneParagrafo(LocalDateTime dataCreazioneParagrafo) {
		this.dataCreazioneParagrafo = dataCreazioneParagrafo;
	}

	public LocalDateTime getDataModificaParagrafo() {
		return dataModificaParagrafo;
	}

	public void setDataModificaParagrafo(LocalDateTime dataModificaParagrafo) {
		this.dataModificaParagrafo = dataModificaParagrafo;
	}

	public Boolean getFlgCancellatoParagrafo() {
		return flgCancellatoParagrafo;
	}

	public void setFlgCancellatoParagrafo(Boolean flgCancellatoParagrafo) {
		this.flgCancellatoParagrafo = flgCancellatoParagrafo;
	}

	public UtenteEntity getUtenteParagrafo() {
		return utenteParagrafo;
	}

	public void setUtenteParagrafo(UtenteEntity utenteParagrafo) {
		this.utenteParagrafo = utenteParagrafo;
	}

	public SezioneEntity getSezioneParagrafo() {
		return sezioneParagrafo;
	}

	public void setSezioneParagrafo (SezioneEntity sezioneParagrafo) {
		this.sezioneParagrafo = sezioneParagrafo;
	}

	@Override
	public String toString() {
		return "ParagrafoEntity [idParagrafo=" + idParagrafo + ", titoloParagrafo=" + titoloParagrafo
				+ ", descrizioneParagrafo=" + descrizioneParagrafo + ", dataCreazioneParagrafo="
				+ dataCreazioneParagrafo + ", dataModificaParagrafo=" + dataModificaParagrafo
				+ ", flgCancellatoParagrafo=" + flgCancellatoParagrafo + ", utenteParagrafo=" + utenteParagrafo
				+ ", sezioneParagrafo=" + sezioneParagrafo + "]";
	}

	
	
}
