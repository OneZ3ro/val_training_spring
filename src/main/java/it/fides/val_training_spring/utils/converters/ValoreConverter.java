package it.fides.val_training_spring.utils.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fides.val_training_spring.models.dto.ParagrafoDto;
import it.fides.val_training_spring.models.dto.UtenteDto;
import it.fides.val_training_spring.models.dto.ValoreDto;
import it.fides.val_training_spring.models.entities.ParagrafoEntity;
import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.models.entities.ValoreEntity;
import it.fides.val_training_spring.models.repositories.ParagrafoRepository;
import it.fides.val_training_spring.models.repositories.UtenteRepository;

@Component
public class ValoreConverter {
	
	@Autowired
	private ParagrafoConverter paragrafoConverter;
	
	@Autowired
	private ParagrafoRepository paragrafoRepository;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private UtenteConverter utenteConverter;
	
	
	public ValoreEntity toEntity(ValoreDto valoreDto) {
		
		ValoreEntity valoreEntity = new ValoreEntity();
		
		valoreEntity.setIdValore(valoreDto.getIdValore());
		valoreEntity.setNomeValore(valoreDto.getNomeValore());
		valoreEntity.setVotoValore(valoreDto.getVotoValore());
		
		ParagrafoEntity paragrafoEntity = paragrafoRepository.findById(valoreDto.getParagrafo()).get();
		valoreEntity.setParagrafo(paragrafoEntity);
		
		UtenteEntity utenteEntity = utenteRepository.findById(valoreDto.getUtente()).get();
		paragrafoEntity.setUtente(utenteEntity);
		
		return valoreEntity;
	}
	
	public ValoreDto toDto(ValoreEntity valoreEntity) {
		
		ValoreDto valoreDto = new ValoreDto();
		
		valoreDto.setIdValore(valoreEntity.getIdValore());
		valoreDto.setNomeValore(valoreEntity.getNomeValore());
		valoreDto.setVotoValore(valoreEntity.getVotoValore());
		
		UtenteDto utenteDto = utenteConverter.toDto(valoreEntity.getUtente());
		ParagrafoDto paragrafoDto = paragrafoConverter.toDto(valoreEntity.getParagrafo());
		valoreDto.setUtente(utenteDto.getIdUtente());
		valoreDto.setParagrafo(paragrafoDto.getIdParagrafo());
		
		return valoreDto;
	}
}
