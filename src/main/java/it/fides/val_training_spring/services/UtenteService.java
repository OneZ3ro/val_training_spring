package it.fides.val_training_spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fides.val_training_spring.exceptions.NotFoundException;
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
    
    public UtenteEntity insertUtente(UtenteEntity utenteEntity) {
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
    		
    		utente.setIdUtente(utenteEntity.getIdUtente());
    		utente.setNomeUtente(utenteEntity.getNomeUtente());
    		utente.setCognomeUtente(utenteEntity.getCognomeUtente());
    		utente.setEmailUtente(utenteEntity.getEmailUtente());
    		utente.setInformazioniGeneraliUtente(utenteEntity.getInformazioniGeneraliUtente());
    		utente.setDataCreazioneUtente(utenteEntity.getDataCreazioneUtente());
    		utente.setDataModificaUtente(utenteEntity.getDataModificaUtente());
    		utente.setFlgCancellatoUtente(utenteEntity.isFlgCancellatoUtente());
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
    
    public UtenteEntity findById(long id) throws NotFoundException {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    
    public UtenteEntity findByEmail(String email) throws Exception {
        return utenteRepository.findByEmailUtente(email)
                .orElseThrow(() -> new Exception("Utente con email "+ email + " non trovato"));
    }
    
    public UtenteEntity trashUtente(Long id, UtenteEntity utenteEntity) {
		UtenteEntity utente = utenteRepository.findById(id).get();
		UtenteEntity trashUtente = null;
		
		if (utente != null && !utente.isFlgCancellatoUtente()) {
			utente.setFlgCancellatoUtente(true);
			trashUtente = utenteRepository.save(utente);
			utenteLogger.log.info("Utente spostato nel cestino: " + trashUtente);
		} else {
			utenteLogger.log.info("Utente non spostato nel cestino");
		}
		return trashUtente;
	}
}
