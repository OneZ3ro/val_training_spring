package it.fides.val_training_spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.models.repositories.UtenteRepository;
import it.fides.val_training_spring.utils.loggers.UtenteLogger;

@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private UtenteLogger utenteLogger;

    public List<UtenteEntity> getAllUtenti() {        
        List<UtenteEntity> utenti = utenteRepository.findAll();

        if (utenti.size() > 0) {
            utenteLogger.log.info("Utenti: " + utenti);
        } else {
            utenteLogger.log.error("Utenti non trovati");
        }
        return utenti;
    }
    
    public UtenteEntity getUtente(Long id) {
    	UtenteEntity utente = utenteRepository.findById(id).get();
    	
    	if (utente != null) {
    		utenteLogger.log.info("Utente: " + utente);
    	} else {
    		utenteLogger.log.error("Utente non trovato");
    	}
    	return utente;
    }
    
    public UtenteEntity saveUtente(UtenteEntity utenteEntity) {
    	UtenteEntity utente = utenteRepository.save(utenteEntity);
    	if (utente != null) {
    		utenteLogger.log.info("Utente: " + utente);
    	} else {
    		utenteLogger.log.error("Utente non salvato");
    	}
    	return utente;	
    }
    
    public UtenteEntity updateUtente(Long id, UtenteEntity utenteEntity) {
    	UtenteEntity utente = utenteRepository.findById(id).get();
    	UtenteEntity updatedUtente = null;
    	
    	if (utente != null) {
    		utenteLogger.log.info("Utente: " + utente);
    		
    		utente.setNomeUtente(utenteEntity.getNomeUtente());
    		utente.setCognomeUtente(utenteEntity.getCognomeUtente());
    		utente.setEmailUtente(utenteEntity.getEmailUtente());
    		utente.setPasswordUtente(utenteEntity.getPasswordUtente());
    		utente.setInformazioniGeneraliUtente(utenteEntity.getInformazioniGeneraliUtente());
    		utente.setDataModificaUtente(utenteEntity.getDataModificaUtente());
    		utente.setFlgCancellato(utenteEntity.isFlgCancellato());
    		utente.setRuolo(utenteEntity.getRuolo());
    		utente.setGruppi(utenteEntity.getGruppi());
    		
    		updatedUtente = utenteRepository.save(utente);
    		utenteLogger.log.info("Utente aggiornato: " + updatedUtente);
    	} else {
    		utenteLogger.log.error("Utente non aggiornato");
    	}
    	return updatedUtente;
    }

    public void deleteUtente(Long id) {
    	utenteRepository.deleteById(id);
    }
}
