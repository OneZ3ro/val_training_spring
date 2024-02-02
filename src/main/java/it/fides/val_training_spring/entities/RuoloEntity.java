package it.fides.val_training_spring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RuoloEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRuolo;
	
	@Column(name = "nome")
	private String nomeRuolo;
	
	public RuoloEntity() {}

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
		return
				"RUOLO: {\n" +
				"Id: " + getIdRuolo() + "\n" +
				"Nome: " + getNomeRuolo() + "\n}";
	}
}
