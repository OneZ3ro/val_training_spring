package it.fides.val_training_spring.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import it.fides.val_training_spring.exceptions.NotFoundException;
import it.fides.val_training_spring.models.dto.UtenteUpdateDto;
import it.fides.val_training_spring.models.entities.GruppoEntity;
import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.models.repositories.UtenteRepository;
import it.fides.val_training_spring.security.EmailService;
import it.fides.val_training_spring.utils.loggers.UtenteLogger;

@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private GruppoService gruppoService;
	
	@Autowired
	private RuoloService ruoloService;
	
	@Autowired
	private UtenteLogger utenteLogger;
	
	@Autowired
	private EmailService emailService;

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
    
    public UtenteEntity updateUtente(Long id, UtenteUpdateDto body) {
        UtenteEntity utente = utenteRepository.findById(id).get();
        UtenteEntity updatedUtente = null;
        List<GruppoEntity> gruppi = new ArrayList<>();

        if (utente != null) {
            utenteLogger.log.info("Utente: " + utente);
            if(body.nome() != null) {
                utente.setNomeUtente(body.nome());
            }
            if(body.cognome() != null) {
                utente.setCognomeUtente(body.cognome());;
            }
            if(body.email() != null) {
                utente.setEmailUtente(body.email());
            }
            if(body.informazioniGenerali() != null) {
                utente.setInformazioniGeneraliUtente(body.informazioniGenerali());
            }
            if(body.gruppiId() != null) {
                System.out.println("è entrato");
                System.out.println("è entrato");
                System.out.println("è entrato");
                System.out.println("è entrato");
                for (int i = 0; i < body.gruppiId().size(); i++) {
                    GruppoEntity gruppo = gruppoService.getGruppo(body.gruppiId().get(i));
                    gruppi.add(gruppo);
                }
                utente.setGruppi(gruppi);
            } else {
                System.out.println("non entra");
            }
            if(body.ruolo() != null) {
                utente.setRuolo(ruoloService.getRuolo(body.ruolo()));
            }
            utente.setDataModificaUtente(LocalDateTime.now());
            utente.setFlgCancellatoUtente(false);
            updatedUtente = utenteRepository.save(utente);
            utenteLogger.log.info("Utente aggiornato: " + updatedUtente);
        } else {
            utenteLogger.log.error("Utente non aggiornato");
        }
        return updatedUtente;
    }
}
