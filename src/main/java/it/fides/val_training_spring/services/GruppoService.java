package it.fides.val_training_spring.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fides.val_training_spring.models.dto.CreazioneGruppoDto;
import it.fides.val_training_spring.models.dto.GruppoDto;
import it.fides.val_training_spring.models.entities.GruppoEntity;
import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.models.repositories.GruppoRepository;
import it.fides.val_training_spring.models.repositories.UtenteRepository;
import it.fides.val_training_spring.utils.loggers.GruppoLogger;

@Service
public class GruppoService {
	
	@Autowired
	private GruppoRepository gruppoRepository;
	
	@Autowired
	private GruppoLogger gruppoLogger;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	
	
	public List<GruppoEntity> getAllGruppi() {
		List<GruppoEntity> gruppi = gruppoRepository.findAll();
		
		if (gruppi.size() > 0) {
			gruppoLogger.log.info("Gruppi: " + gruppi);
		} else {
			gruppoLogger.log.error("Gruppi non trovati");
		}
		
		return gruppi;
	}
	
	public GruppoEntity getGruppo(Long id) {
		GruppoEntity gruppo = gruppoRepository.findById(id).get();
		System.out.println("entra nel nostro caso");
		List<UtenteEntity> app = new ArrayList<>();
		
		for(UtenteEntity utente : gruppo.getDipendenti()) {
			UtenteEntity newUtente = new UtenteEntity();
			newUtente.setIdUtente(utente.getIdUtente());
			newUtente.setNomeUtente(utente.getNomeUtente());
			newUtente.setCognomeUtente(utente.getCognomeUtente());
			/*newUtente.setEmailUtente(utente.getEmailUtente());
			newUtente.setInformazioniGeneraliUtente(utente.getInformazioniGeneraliUtente());
			newUtente.setDataCreazioneUtente(utente.getDataCreazioneUtente());
			newUtente.setDataModificaUtente(utente.getDataModificaUtente());
			newUtente.setFlgCancellatoUtente(utente.isFlgCancellatoUtente());
			newUtente.setRuolo(utente.getRuolo());*/
			app.add(newUtente);
		}
		gruppo.setDipendenti(app);
		
		if (gruppo != null) {
			gruppoLogger.log.info("Gruppo: " + gruppo);
		}
		
		return gruppo;
	}
	
	public GruppoEntity insertGruppo(CreazioneGruppoDto gruppoEntity) {
		GruppoEntity gruppo = new GruppoEntity();
		gruppo.setNomeGruppo(gruppoEntity.nomeGruppo());
		gruppo.setResponsabile(utenteRepository.findById(gruppoEntity.responsabile()).get());
		gruppo.setDataCreazioneGruppo(LocalDateTime.now());
		gruppo.setDataModificaGruppo(LocalDateTime.now());
		gruppo.setFlgCancellatoGruppo(false);
		
		return gruppoRepository.save(gruppo);
		/*
		if (gruppo != null) {
			gruppoLogger.log.info("Gruppo: " + gruppo);
		} else {
			gruppoLogger.log.error("Gruppo non creato");
		} */
		
	}
	
	public GruppoEntity updateGruppo(Long id, GruppoDto body) {
		GruppoEntity gruppo = gruppoRepository.findById(id).get();
		GruppoEntity updatedGruppo = null;
		List<UtenteEntity> dipendentiSet = new ArrayList<UtenteEntity>();
		
		if (gruppo != null) {
			if(body.getNomeGruppo() != null) {
				gruppo.setNomeGruppo(body.getNomeGruppo());
			}
			if(body.getResponsabile() != null) {
				gruppo.setResponsabile(utenteRepository.findById(body.getResponsabile()).get());
			}
			if(body.getDipendenti() != null) {
				for (int i = 0; i < body.getDipendenti().size(); i++) {
    				UtenteEntity utente = utenteRepository.findById(body.getResponsabile()).get();
    				dipendentiSet.add(utente);
    			}
			}
			gruppo.setDataModificaGruppo(LocalDateTime.now());
			
			updatedGruppo = gruppoRepository.save(gruppo);
			gruppoLogger.log.info("Gruppo aggiornato: " + updatedGruppo);
		} else {
			gruppoLogger.log.error("Gruppo non aggiornato");
		}
		return updatedGruppo;
	}
	
	public void deleteGruppo(Long id) {
		gruppoRepository.deleteById(id);
	}
	
	public GruppoEntity trashGruppo(Long id, GruppoEntity gruppoEntity) {
		GruppoEntity gruppo = gruppoRepository.findById(id).get();
		GruppoEntity trashGruppo = null;
		
		if (gruppo != null && !gruppo.isFlgCancellatoGruppo()) {
			gruppo.setFlgCancellatoGruppo(true);
			trashGruppo = gruppoRepository.save(gruppo);
			gruppoLogger.log.info("Gruppo spostato nel cestino: " + trashGruppo);
		} else {
			gruppoLogger.log.info("Gruppo non spostato nel cestino");
		}
		return trashGruppo;
	}
}
