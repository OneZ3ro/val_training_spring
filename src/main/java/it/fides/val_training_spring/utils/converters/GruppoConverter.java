package it.fides.val_training_spring.utils.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fides.val_training_spring.models.dto.GruppoDto;
import it.fides.val_training_spring.models.dto.UtenteDto;
import it.fides.val_training_spring.models.entities.GruppoEntity;
import it.fides.val_training_spring.models.entities.UtenteEntity;

@Component
public class GruppoConverter {
	
	@Autowired
	private UtenteConverter utenteConverter;
	
	public GruppoEntity toEntity(GruppoDto gruppoDto) {
		
		GruppoEntity gruppoEntity = new GruppoEntity();
		
		gruppoEntity.setIdGruppo(gruppoDto.getIdGruppo());
		gruppoEntity.setNomeGruppo(gruppoDto.getNomeGruppo());
		gruppoEntity.setResponsabile(utenteConverter.toEntity(gruppoDto.getResponsabile()));
		
		List<UtenteEntity> utenteEntityList = new ArrayList<>();
		for (UtenteDto utenteDto : gruppoDto.getDipendenti()) {
			utenteEntityList.add(utenteConverter.toEntity(utenteDto));
		}
		gruppoEntity.setDipendenti(utenteEntityList);
		
		return gruppoEntity;
	}
	
	public GruppoDto toDto(GruppoEntity gruppoEntity) {
		
		GruppoDto gruppoDto = new GruppoDto();
		
		gruppoDto.setIdGruppo(gruppoEntity.getIdGruppo());
		gruppoDto.setNomeGruppo(gruppoEntity.getNomeGruppo());
		gruppoDto.setResponsabile(utenteConverter.toDto(gruppoEntity.getResponsabile()));
		
		
		List<UtenteDto> utenteDtoList = new ArrayList<>();
		for (UtenteEntity utenteEntity : gruppoEntity.getDipendenti()) {
			utenteDtoList.add(utenteConverter.toDto(utenteEntity));
		}
		gruppoDto.setDipendenti(utenteDtoList);
		
		return gruppoDto;
	}
}
