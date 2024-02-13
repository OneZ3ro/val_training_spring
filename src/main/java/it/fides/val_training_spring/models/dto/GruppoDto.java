package it.fides.val_training_spring.models.dto;

import java.util.List;

public class GruppoDto {

	private Long idGruppo;
	private String nomeGruppo;
	private Long responsabile;
    private List<Long> dipendenti;
    
    public GruppoDto() {}

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

	public Long getResponsabile() {
		return responsabile;
	}

	public void setResponsabile(Long responsabile) {
		this.responsabile = responsabile;
	}

	public List<Long> getDipendenti() {
		return dipendenti;
	}

	public void setDipendenti(List<Long> dipendenti) {
		this.dipendenti = dipendenti;
	}

	@Override
	public String toString() {
		return "GruppoDto [idGruppo=" + idGruppo + ", nomeGruppo=" + nomeGruppo + ", responsabile=" + responsabile
				+ ", dipendenti=" + dipendenti + "]";
	}	
}
