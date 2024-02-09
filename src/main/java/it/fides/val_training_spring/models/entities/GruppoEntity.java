package it.fides.val_training_spring.models.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="gruppo")
//@JsonIgnoreProperties({"dipendenti"})
public class GruppoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_gruppo")
	private Long idGruppo;
	
	@Column(name = "nome", nullable=false, unique = true, length = 64)
	private String nomeGruppo;
	
	@Column(name = "data_creazione", nullable=false)
	private LocalDateTime dataCreazioneGruppo;
	
	@Column(name = "data_modifica", nullable=false)
	private LocalDateTime dataModificaGruppo;
	
	@Column(name = "flg_cancellato", nullable=false)
	private boolean flgCancellatoGruppo;
	
	@OneToOne
	@JoinColumn(name = "id_responsabile")
	private UtenteEntity responsabile;
	
	@ManyToMany
    @JoinTable(
            name = "utente_gruppo",
            joinColumns = @JoinColumn(name = "id_gruppo"),
            inverseJoinColumns = @JoinColumn(name = "id_utente"))
    private List<UtenteEntity> dipendenti;
	
	public GruppoEntity() {}

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

	public LocalDateTime getDataCreazioneGruppo() {
		return dataCreazioneGruppo;
	}

	public void setDataCreazioneGruppo(LocalDateTime dataCreazioneGruppo) {
		this.dataCreazioneGruppo = dataCreazioneGruppo;
	}

	public LocalDateTime getDataModificaGruppo() {
		return dataModificaGruppo;
	}

	public void setDataModificaGruppo(LocalDateTime dataModificaGruppo) {
		this.dataModificaGruppo = dataModificaGruppo;
	}

	public boolean isFlgCancellatoGruppo() {
		return flgCancellatoGruppo;
	}

	public void setFlgCancellatoGruppo(boolean flgCancellatoGruppo) {
		this.flgCancellatoGruppo = flgCancellatoGruppo;
	}

	public UtenteEntity getResponsabile() {
		return responsabile;
	}

	public void setResponsabile(UtenteEntity responsabile) {
		this.responsabile = responsabile;
	}

	public List<UtenteEntity> getDipendenti() {
		return dipendenti;
	}

	public void setDipendenti(List<UtenteEntity> dipendenti) {
		this.dipendenti = dipendenti;
	}

	@Override
	public String toString() {
		List<UtenteEntity> dipendentiList = dipendenti;
		String app = "";
		for(UtenteEntity utente : dipendentiList) {
			app = app + utente.getNomeUtente() + ", ";
		}
		System.out.println("app" + app);
		return "GruppoEntity [idGruppo=" + idGruppo + ", nomeGruppo=" + nomeGruppo + ", dataCreazioneGruppo="
				+ dataCreazioneGruppo + ", dataModificaGruppo=" + dataModificaGruppo + ", flgCancellatoGruppo="
				+ flgCancellatoGruppo + ", dipendenti : " + app + "]";
	}
}
