package it.fides.val_training_spring.utils.converters;

import it.fides.val_training_spring.models.dto.SezioneDto;
import it.fides.val_training_spring.models.entities.SezioneEntity;

public class SezioneConverter {
	
	public SezioneEntity toEntity(SezioneDto sezioneDto) {
		
		SezioneEntity sezioneEntity = new SezioneEntity();
		
		sezioneEntity.setIdSezione(sezioneDto.getIdSezione());
		sezioneEntity.setTitoloSezione(sezioneDto.getTitoloSezione());
		
		return sezioneEntity;
	}
	
	public SezioneDto toDto(SezioneEntity sezioneEntity) {
		
		SezioneDto sezioneDto = new SezioneDto();
		
		sezioneDto.setIdSezione(sezioneEntity.getIdSezione());
		sezioneDto.setTitoloSezione(sezioneEntity.getTitoloSezione());
		
		return sezioneDto;
	}
}
