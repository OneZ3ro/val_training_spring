package it.fides.val_training_spring.utils.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fides.val_training_spring.models.dto.ParagrafoDto;
import it.fides.val_training_spring.models.dto.ScalaDto;
import it.fides.val_training_spring.models.dto.SezioneDto;
import it.fides.val_training_spring.models.dto.UtenteDto;
import it.fides.val_training_spring.models.entities.ScalaEntity;
import it.fides.val_training_spring.models.entities.SezioneEntity;
import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.models.repositories.ParagrafoRepository;
import it.fides.val_training_spring.models.repositories.SezioneRepository;
import it.fides.val_training_spring.models.entities.ParagrafoEntity;

@Component
public class ScalaConverter {
	
	@Autowired
	ParagrafoConverter paragrafoConverter;
	
	@Autowired
	ParagrafoRepository paragrafoRepository;
	
	@Autowired
	SezioneRepository sezioneRepository;
	
	@Autowired
	SezioneConverter sezioneConverter;
	
	public ScalaEntity toEntity(ScalaDto scalaDto) {
		
		ScalaEntity scalaEntity = new ScalaEntity();
		
		scalaEntity.setTitoloScala(scalaDto.getTitoloScala());
		scalaEntity.setDescrizioneScala(scalaDto.getDescrizioneScala());
		
		 ParagrafoEntity paragrafoEntity = paragrafoRepository.findById(scalaDto.getParagrafo()).get();
		scalaEntity.setParagrafo(paragrafoEntity);
		
		SezioneEntity sezioneEntity = sezioneRepository.findById(scalaDto.getSezione()).get();
		scalaEntity.setSezione(sezioneEntity);
		
		return scalaEntity;
	}
	
	public ScalaDto toEntity(ScalaEntity scalaEntity) {
		
		ScalaDto scalaDto = new ScalaDto();
		
		scalaDto.setIdScala(scalaEntity.getIdScala());
		scalaDto.setTitoloScala(scalaEntity.getTitoloScala());
		scalaDto.setDescrizioneScala(scalaEntity.getDescrizioneScala());
		
		SezioneDto sezioneDto = sezioneConverter.toDto(scalaEntity.getSezione());
		scalaDto.setSezione(sezioneDto.getIdSezione());
		
		ParagrafoDto paragrafoDto = paragrafoConverter.toDto(scalaEntity.getParagrafo());
		scalaDto.setParagrafo(paragrafoDto.getIdParagrafo());
		
		
		return scalaDto;
	}
}
