package it.fides.val_training_spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.fides.val_training_spring.models.entities.ParagrafoEntity;
import it.fides.val_training_spring.models.repositories.ParagrafoRepository;
import it.fides.val_training_spring.utils.loggers.ParagrafoLogger;

@Service
public class ParagrafoService {
	
	@Autowired
	private ParagrafoRepository paragrafoRepository;
	
	@Autowired
	private ParagrafoLogger paragrafoLogger;
	
	public List<ParagrafoEntity> getAllParagrafi() {
		
		List<ParagrafoEntity> paragrafi = paragrafoRepository.findAll(); 
		if(paragrafi.size() > 0) {
			paragrafoLogger.log.info("Paragrafi: " + paragrafi);
		} else {
			paragrafoLogger.log.error("Utenti non trovati");
		}
		return paragrafi;
	}
	
	public ParagrafoEntity getParagrafo(Long id) {
		ParagrafoEntity paragrafo = paragrafoRepository.findById(id).get();
		
		if (paragrafo != null) {
			paragrafoLogger.log.info("Paragarfo: " + paragrafo);
		} else {
			paragrafoLogger.log.error("Paragrafo non trovato");
		}
		
		return paragrafo;
	}
	
	public ParagrafoEntity insertParagrafo(ParagrafoEntity paragrafoEntity) {
		ParagrafoEntity paragrafo = paragrafoRepository.save(paragrafoEntity);
		
		if (paragrafo != null) {
			paragrafoLogger.log.info("Paragrafo: " + paragrafo);
		} else {
			paragrafoLogger.log.error("Paragrafo non trovato");
		}
		
		return paragrafo;
	}
	
    public ParagrafoEntity updateParagrafo(Long id, ParagrafoEntity paragrafoEntity) {
    	ParagrafoEntity paragrafo = paragrafoRepository.findById(id).get();
    	ParagrafoEntity updatedParagrafo = null;
    	
    	if (paragrafo != null) {
    		paragrafo.setIdParagrafo(paragrafoEntity.getIdParagrafo());
    		paragrafo.setTitoloParagrafo(paragrafoEntity.getTitoloParagrafo());
    		paragrafo.setDescrizioneParagrafo(paragrafoEntity.getDescrizioneParagrafo());
    		paragrafo.setDataCreazioneParagrafo(paragrafoEntity.getDataCreazioneParagrafo());
    		paragrafo.setDataModificaParagrafo(paragrafoEntity.getDataModificaParagrafo());
    		paragrafo.setFlgCancellatoParagrafo(paragrafoEntity.isFlgCancellatoParagrafo());
    		paragrafo.setUtente(paragrafoEntity.getUtente());
    		paragrafo.setSezione(paragrafoEntity.getSezione());
    		
    		updatedParagrafo = paragrafoRepository.save(paragrafo);
    		paragrafoLogger.log.info("Paragrafo aggiornato: " + updatedParagrafo);
    	} else {
    		paragrafoLogger.log.error("Paragrafo non aggiornato");
    	}
    	
		return updatedParagrafo;
    }
    
	public void deleteParagrafo(Long id) {
		paragrafoRepository.deleteById(id);
	}
	
	public ParagrafoEntity trashParagrafo(Long id, ParagrafoEntity paragrafoEntity) {
		ParagrafoEntity paragrafo = paragrafoRepository.findById(id).get();
		ParagrafoEntity trashParagrafo = null;
		
		if (paragrafo != null && !paragrafo.isFlgCancellatoParagrafo()) {
			paragrafo.setFlgCancellatoParagrafo(true);
			trashParagrafo = paragrafoRepository.save(paragrafo);
			paragrafoLogger.log.info("Gruppo spostato nel cestino: " + trashParagrafo);
		} else {
			paragrafoLogger.log.info("Gruppo non spostato nel cestino");
		}
		return trashParagrafo;
	}
}
