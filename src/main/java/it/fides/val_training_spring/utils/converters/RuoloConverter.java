package it.fides.val_training_spring.utils.converters;

import org.springframework.stereotype.Component;

import it.fides.val_training_spring.models.dto.RuoloDto;
import it.fides.val_training_spring.models.entities.RuoloEntity;

@Component
public class RuoloConverter {
	
	public RuoloEntity toEntity(RuoloDto ruoloDto) {
		
		RuoloEntity ruoloEntity = new RuoloEntity();
		
		ruoloEntity.setIdRuolo(ruoloDto.getIdRuolo());
		ruoloEntity.setNomeRuolo(ruoloDto.getNomeRuolo());
		
		return ruoloEntity;
	}
	
	public RuoloDto toDto(RuoloEntity ruoloEntity) {
		
		RuoloDto ruoloDto = new RuoloDto();
		
		ruoloDto.setIdRuolo(ruoloEntity.getIdRuolo());
		ruoloDto.setNomeRuolo(ruoloEntity.getNomeRuolo());
		
		return ruoloDto;
	}
}
