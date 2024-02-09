package it.fides.val_training_spring.utils.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fides.val_training_spring.models.dto.ParagrafoDto;
import it.fides.val_training_spring.models.dto.SezioneDto;
import it.fides.val_training_spring.models.dto.UtenteDto;
import it.fides.val_training_spring.models.entities.ParagrafoEntity;
import it.fides.val_training_spring.models.entities.SezioneEntity;
import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.models.repositories.SezioneRepository;
import it.fides.val_training_spring.services.UtenteService;

@Component
public class ParagrafoConverter {
	
	@Autowired
	private UtenteConverter utenteConverter;
	
	@Autowired
	private SezioneConverter sezioneConverter;
	
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
	private SezioneRepository sezioneRepository;
	
	public ParagrafoEntity toEntity(ParagrafoDto paragrafoDto) {
		
		ParagrafoEntity paragrafoEntity = new ParagrafoEntity();
		
		paragrafoEntity.setTitoloParagrafo(paragrafoDto.getTitoloParagrafo());
		paragrafoEntity.setDescrizioneParagrafo(paragrafoDto.getDescrizioneParagrafo());
		UtenteEntity utenteEntity = utenteService.findById(paragrafoDto.getUtente());
		SezioneEntity sezioneEntity = sezioneRepository.findById(paragrafoDto.getSezione()).get();
		paragrafoEntity.setUtente(utenteEntity);
		paragrafoEntity.setSezione(sezioneEntity);
		
		return paragrafoEntity;
	}
	
	public ParagrafoDto toDto(ParagrafoEntity paragrafoEntity) {
		
		ParagrafoDto paragrafoDto = new ParagrafoDto();
		
		paragrafoDto.setIdParagrafo(paragrafoEntity.getIdParagrafo());
		paragrafoDto.setTitoloParagrafo(paragrafoEntity.getTitoloParagrafo());
		paragrafoDto.setDescrizioneParagrafo(paragrafoEntity.getDescrizioneParagrafo());
		UtenteDto utenteDto = utenteConverter.toDto(paragrafoEntity.getUtente());
		SezioneDto sezioneDto = sezioneConverter.toDto(paragrafoEntity.getSezione());
		paragrafoDto.setUtente(utenteDto.getIdUtente());
		paragrafoDto.setSezione(sezioneDto.getIdSezione());
		
		return paragrafoDto;
	}
}
