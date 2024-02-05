package it.fides.val_training_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fides.val_training_spring.models.entities.*;
import it.fides.val_training_spring.models.repositories.*;
import it.fides.val_training_spring.utils.logger.ParagrafoLogger;
import it.fides.val_training_spring.utils.logger.UtenteLogger;

@Service
public class ParagrafoService {
	@Autowired
	private ParagrafoRepository paragrafoRepository;
	
	@Autowired
	private ParagrafoLogger paragrafoLogger;
	
	public List<ParagrafoEntity> getAllParagrafi() {
		
		List<ParagrafoEntity> listaParagrafi = paragrafoRepository.findAll(); 
		if(listaParagrafi.size() > 0) {
			for(ParagrafoEntity paragrafi: listaParagrafi) paragrafoLogger.log.info("Paragrafi: "+paragrafi);
		} else {
			paragrafoLogger.log.error("Utenti non trovati");
		}
		return listaParagrafi;
	}
	
	public Optional<ParagrafoEntity> getParagrafoById(Long id) {
		return paragrafoRepository.findById(id);
	}
	
    public ParagrafoEntity updateParagrafo(ParagrafoEntity paragrafoEntity, Long id) {
    	ParagrafoEntity paragrafo = paragrafoRepository.findById(id).get();
    	paragrafo.setTitoloParagrafo(paragrafoEntity.getTitoloParagrafo());
    	paragrafo.setDescrizioneParagrafo(paragrafoEntity.getDescrizioneParagrafo());
    	paragrafo.setUtente(paragrafoEntity.getUtente());
    	paragrafo.setSezione(paragrafoEntity.getSezione());
		return paragrafo;
    }
}
