package it.fides.val_training_spring.utils.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fides.val_training_spring.models.dto.ScalaDto;
import it.fides.val_training_spring.models.entities.ScalaEntity;

@Component
public class ScalaConverter {
	
	@Autowired
	ParagrafoConverter paragrafoConverter;
	
	public ScalaEntity toEntity(ScalaDto scalaDto) {
		
		ScalaEntity scalaEntity = new ScalaEntity();
		
		scalaEntity.setIdScala(scalaDto.getIdScala());
		scalaEntity.setTitoloScala(scalaDto.getTitoloScala());
		scalaEntity.setDescrizioneScala(scalaDto.getDescrizioneScala());
		scalaEntity.setParagrafo(paragrafoConverter.toEntity(scalaDto.getParagrafo()));
		
		return scalaEntity;
	}
	
	public ScalaDto toEntity(ScalaEntity scalaEntity) {
		
		ScalaDto scalaDto = new ScalaDto();
		
		scalaDto.setIdScala(scalaEntity.getIdScala());
		scalaDto.setTitoloScala(scalaEntity.getTitoloScala());
		scalaDto.setDescrizioneScala(scalaEntity.getDescrizioneScala());
		scalaDto.setParagrafo(paragrafoConverter.toDto(scalaEntity.getParagrafo()));
		
		return scalaDto;
	}
}
