package it.fides.val_training_spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.fides.val_training_spring.models.entities.GruppoEntity;
import it.fides.val_training_spring.models.repositories.GruppoRepository;
import it.fides.val_training_spring.utils.loggers.GruppoLogger;

@Service
public class GruppoService {
	
	@Autowired
	private GruppoRepository gruppoRepository;
	
	@Autowired
	private GruppoLogger gruppoLogger;
	
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
		
		if (gruppo != null) {
			gruppoLogger.log.info("Gruppo: " + gruppo);
		} else {
			gruppoLogger.log.error("Gruppo non trovato");
		}
		
		return gruppo;
	}
	
	public GruppoEntity insertGruppo(GruppoEntity gruppoEntity) {
		GruppoEntity gruppo = gruppoRepository.save(gruppoEntity);
		
		if (gruppo != null) {
			gruppoLogger.log.info("Gruppo: " + gruppo);
		} else {
			gruppoLogger.log.error("Gruppo non trovato");
		}
		
		return gruppo;
	}
	
	public GruppoEntity updateGruppo(Long id, GruppoEntity gruppoEntity) {
		GruppoEntity gruppo = gruppoRepository.findById(id).get();
		GruppoEntity updatedGruppo = null;
		
		if (gruppo != null) {
			gruppo.setIdGruppo(gruppoEntity.getIdGruppo());
			gruppo.setNomeGruppo(gruppoEntity.getNomeGruppo());
			gruppo.setDataCreazione(gruppoEntity.getDataCreazione());
			gruppo.setDataModifica(gruppoEntity.getDataModifica());
			gruppo.setFlgCancellato(gruppoEntity.isFlgCancellato());
			gruppo.setResponsabile(gruppoEntity.getResponsabile());
			gruppo.setDipendenti(gruppoEntity.getDipendenti());
			
			updatedGruppo = gruppoRepository.save(gruppo);
			gruppoLogger.log.info("Ruolo aggiornato: " + updatedGruppo);
		} else {
			gruppoLogger.log.error("Ruolo non aggiornato");
		}
		
		return updatedGruppo;
	}
	
	public void deleteGruppo(Long id) {
		gruppoRepository.deleteById(id);
	}
}
