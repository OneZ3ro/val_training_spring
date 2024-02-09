package it.fides.val_training_spring.utils.converters;

import org.springframework.stereotype.Component;

import it.fides.val_training_spring.models.dto.SezioneDto;
import it.fides.val_training_spring.models.entities.SezioneEntity;

@Component
public class SezioneConverter {
	
	public SezioneEntity toEntity(SezioneDto sezioneDto) {
		
		SezioneEntity sezioneEntity = new SezioneEntity();
		
		sezioneEntity.setTitoloSezione(sezioneDto.getTitoloSezione());
		
		return sezioneEntity;
	}
	
	public SezioneDto toDto(SezioneEntity sezioneEntity) {
		
		SezioneDto sezioneDto = new SezioneDto();
		
		sezioneDto.setTitoloSezione(sezioneEntity.getTitoloSezione());
		
		return sezioneDto;
	}
}
