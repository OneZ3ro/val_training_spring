package it.fides.val_training_spring.models.dto;

public class RuoloDto {

	private Long idRuolo;

	private String nomeRuolo;
	
	public RuoloDto() {}

	public Long getIdRuolo() {
		return idRuolo;
	}
	public void setIdRuolo(Long idRuolo) {
		this.idRuolo = idRuolo;
	}
	public String getNomeRuolo() {
		return nomeRuolo;
	}
	public void setNomeRuolo(String nomeRuolo) {
		this.nomeRuolo = nomeRuolo;
	}

	@Override
	public String toString() {
		return "RuoloDto [idRuolo=" + idRuolo + ", nomeRuolo=" + nomeRuolo + "]";
	}
}
