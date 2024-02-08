package it.fides.val_training_spring.utils.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fides.val_training_spring.models.dto.ValoreDto;
import it.fides.val_training_spring.models.entities.ValoreEntity;

@Component
public class ValoreConverter {
	
	@Autowired
	private ParagrafoConverter paragrafoConverter;
	
	@Autowired
	private UtenteConverter utenteConverter;
	
	public ValoreEntity toEntity(ValoreDto valoreDto) {
		
		ValoreEntity valoreEntity = new ValoreEntity();
		
		valoreEntity.setIdValore(valoreDto.getIdValore());
		valoreEntity.setNomeValore(valoreDto.getNomeValore());
		valoreEntity.setVotoValore(valoreDto.getVotoValore());
		valoreEntity.setParagrafo(paragrafoConverter.toEntity(valoreDto.getParagrafo()));
		valoreEntity.setUtente(utenteConverter.toEntity(valoreDto.getUtente()));
		
		return valoreEntity;
	}
	
	public ValoreDto toDto(ValoreEntity valoreEntity) {
		
		ValoreDto valoreDto = new ValoreDto();
		
		valoreDto.setIdValore(valoreEntity.getIdValore());
		valoreDto.setNomeValore(valoreEntity.getNomeValore());
		valoreDto.setVotoValore(valoreEntity.getVotoValore());
		valoreDto.setParagrafo(paragrafoConverter.toDto(valoreEntity.getParagrafo()));
		valoreDto.setUtente(utenteConverter.toDto(valoreEntity.getUtente()));
		
		return valoreDto;
	}
}
