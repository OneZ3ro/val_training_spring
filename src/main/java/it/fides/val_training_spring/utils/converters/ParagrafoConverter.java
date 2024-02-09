package it.fides.val_training_spring.utils.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.fides.val_training_spring.models.dto.ParagrafoDto;
import it.fides.val_training_spring.models.entities.ParagrafoEntity;

@Component
public class ParagrafoConverter {
	
	@Autowired
	private UtenteConverter utenteConverter;
	
	@Autowired
	private SezioneConverter sezioneConverter;
	
	public ParagrafoEntity toEntity(ParagrafoDto paragrafoDto) {
		
		ParagrafoEntity paragrafoEntity = new ParagrafoEntity();
		
		paragrafoEntity.setIdParagrafo(paragrafoDto.getIdParagrafo());
		paragrafoEntity.setTitoloParagrafo(paragrafoDto.getTitoloParagrafo());
		paragrafoEntity.setDescrizioneParagrafo(paragrafoDto.getDescrizioneParagrafo());
		paragrafoEntity.setUtente(utenteConverter.toEntity(paragrafoDto.getUtente()));
		paragrafoEntity.setSezione(sezioneConverter.toEntity(paragrafoDto.getSezione()));
		
		return paragrafoEntity;
	}
	
	public ParagrafoDto toDto(ParagrafoEntity paragrafoEntity) {
		
		ParagrafoDto paragrafoDto = new ParagrafoDto();
		
		paragrafoDto.setIdParagrafo(paragrafoEntity.getIdParagrafo());
		paragrafoDto.setTitoloParagrafo(paragrafoEntity.getTitoloParagrafo());
		paragrafoDto.setDescrizioneParagrafo(paragrafoEntity.getDescrizioneParagrafo());
		paragrafoDto.setUtente(utenteConverter.toDto(paragrafoEntity.getUtente()));
		paragrafoDto.setSezione(sezioneConverter.toDto(paragrafoEntity.getSezione()));
		
		return paragrafoDto;
	}
}
