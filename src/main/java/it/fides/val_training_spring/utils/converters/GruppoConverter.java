package it.fides.val_training_spring.utils.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fides.val_training_spring.models.dto.GruppoDto;
import it.fides.val_training_spring.models.dto.UtenteDto;
import it.fides.val_training_spring.models.entities.GruppoEntity;
import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.services.UtenteService;

@Component
public class GruppoConverter {
	
	@Autowired
	private UtenteService utenteService;
	
	public GruppoEntity toEntity(GruppoDto gruppoDto) {
		
		GruppoEntity gruppoEntity = new GruppoEntity();
		
		gruppoEntity.setIdGruppo(gruppoDto.getIdGruppo());
		gruppoEntity.setNomeGruppo(gruppoDto.getNomeGruppo());
		UtenteEntity utente = utenteService.getUtente(gruppoDto.getResponsabile());
		gruppoEntity.setResponsabile(utente);
		
		List<UtenteEntity> utenteEntityList = new ArrayList<>();
		for (Long idUtenteDto : gruppoDto.getDipendenti()) {
			utenteEntityList.add(utenteService.getUtente(idUtenteDto));
		}
		gruppoEntity.setDipendenti(utenteEntityList);
		
		return gruppoEntity;
	}
	
	public GruppoDto toDto(GruppoEntity gruppoEntity) {
		
		GruppoDto gruppoDto = new GruppoDto();
		
		gruppoDto.setIdGruppo(gruppoEntity.getIdGruppo());
		gruppoDto.setNomeGruppo(gruppoEntity.getNomeGruppo());
		gruppoDto.setResponsabile(gruppoEntity.getResponsabile().getIdUtente());
		
		
		List<Long> utenteDtoList = new ArrayList<>();
		for (UtenteEntity utenteEntity : gruppoEntity.getDipendenti()) {
			utenteDtoList.add(utenteEntity.getIdUtente());
		}
		gruppoDto.setDipendenti(utenteDtoList);
		
		return gruppoDto;
	}
}
