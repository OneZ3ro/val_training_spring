package it.fides.val_training_spring.entities;

import java.time.LocalDateTime;
import java.util.List;

import it.fides.val_training_spring.entities.SezioneEntity;
import it.fides.val_training_spring.entities.UtenteEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="paragrafo")
public class ParagrafoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idParagrafo;
	
	@Column(name="titolo", length=64, nullable=false, unique=false)
	private String titoloParagrafo;
	
	@Column(name="descrizione", length=500, nullable=true, unique=false)
	private String descrizioneParagrafo;
	
	@Column(name="data_creazione", nullable=false, unique=false)
	private LocalDateTime dataCreazioneParagrafo;
	
	@Column(name="data_modifica", nullable=false, unique=false)
	private LocalDateTime dataModificaParagrafo;
	
	@Column(name="flg_cancellato", nullable=false, unique=false)
	private Boolean flgCancellatoParagrafo;
	
	@ManyToOne
	@JoinColumn(name="utente")
	private List<UtenteEntity> utenteParagrafo;
	
	@ManyToOne
	@JoinColumn(name="sezione")
	private List<SezioneEntity> sezioneParagrafo;
	
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

	public List<UtenteEntity> getUtenteParagrafo() {
		return utenteParagrafo;
	}

	public void setUtenteParagrafo(List<UtenteEntity> utenteParagrafo) {
		this.utenteParagrafo = utenteParagrafo;
	}

	public List<SezioneEntity> getSezioneParagrafo() {
		return sezioneParagrafo;
	}

	public void setSezioneParagrafo(List<SezioneEntity> sezioneParagrafo) {
		this.sezioneParagrafo = sezioneParagrafo;
	}

	
}
