package it.fides.val_training_spring.utils.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.fides.val_training_spring.models.dto.GruppoDto;
import it.fides.val_training_spring.models.dto.UtenteDto;
import it.fides.val_training_spring.models.entities.GruppoEntity;
import it.fides.val_training_spring.models.entities.UtenteEntity;

public class UtenteConverter {
	
	@Autowired
	private RuoloConverter ruoloConverter;
	
	@Autowired
	private GruppoConverter gruppoConverter;
	
	public UtenteEntity toEntity(UtenteDto utenteDto) {
		
		UtenteEntity utenteEntity = new UtenteEntity();
		
		utenteEntity.setIdUtente(utenteDto.getIdUtente());
		utenteEntity.setNomeUtente(utenteDto.getNomeUtente());
		utenteEntity.setCognomeUtente(utenteDto.getCognomeUtente());
		utenteEntity.setEmailUtente(utenteDto.getEmailUtente());
		utenteEntity.setPasswordUtente(utenteDto.getPasswordUtente());
		utenteEntity.setInformazioniGeneraliUtente(utenteDto.getInformazioniGeneraliUtente());
		utenteEntity.setRuolo(ruoloConverter.toEntity(utenteDto.getRuolo()));
		
		List<GruppoEntity> gruppoEntityList = new ArrayList<>();
		for (GruppoDto gruppoDto : utenteDto.getGruppi()) {
			gruppoEntityList.add(gruppoConverter.toEntity(gruppoDto));
		}
		utenteEntity.setGruppi(gruppoEntityList);
		
		return utenteEntity;
	}
	
	public UtenteDto toDto(UtenteEntity utenteEntity) {
		
		UtenteDto utenteDto = new UtenteDto();
		
		utenteDto.setIdUtente(utenteEntity.getIdUtente());
		utenteDto.setNomeUtente(utenteEntity.getNomeUtente());
		utenteDto.setCognomeUtente(utenteEntity.getCognomeUtente());
		utenteDto.setEmailUtente(utenteEntity.getEmailUtente());
		utenteDto.setPasswordUtente(utenteEntity.getPasswordUtente());
		utenteDto.setInformazioniGeneraliUtente(utenteEntity.getInformazioniGeneraliUtente());
		utenteDto.setRuolo(ruoloConverter.toDto(utenteEntity.getRuolo()));
		
		List<GruppoDto> gruppoDtoList = new ArrayList<>();
		for (GruppoEntity gruppoEntity : utenteEntity.getGruppi()) {
			gruppoDtoList.add(gruppoConverter.toDto(gruppoEntity));
		}
		utenteDto.setGruppi(gruppoDtoList);
		
		return utenteDto;
	}
}
