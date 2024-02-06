package it.fides.val_training_spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.fides.val_training_spring.models.entities.RuoloEntity;
import it.fides.val_training_spring.models.repositories.RuoloRepository;
import it.fides.val_training_spring.utils.loggers.RuoloLogger;

@Service
public class RuoloService {
	
	@Autowired
	private RuoloRepository ruoloRepository;
	
	@Autowired
	private RuoloLogger ruoloLogger;
	
	public List<RuoloEntity> getAllRuoli() {
		List<RuoloEntity> ruoli = ruoloRepository.findAll();
		if (ruoli.size() > 0) {
			ruoloLogger.log.info("Ruoli: " + ruoli);
		} else {
			ruoloLogger.log.error("Ruoli non trovati");
		}
		return ruoli;
	}
	
	public RuoloEntity getRuolo(Long id) {
		RuoloEntity ruolo = ruoloRepository.findById(id).get();
		
		if (ruolo != null) {
			ruoloLogger.log.info("Ruolo: " + ruolo);
		} else {
			ruoloLogger.log.error("Ruolo non trovato");
		}
		
		return ruolo;
	}
	
	public RuoloEntity insertRuolo(RuoloEntity ruoloEntity) {
		RuoloEntity ruolo = ruoloRepository.save(ruoloEntity);
		
		if (ruolo != null) {
			ruoloLogger.log.info("Ruolo: " + ruolo);
		} else {
			ruoloLogger.log.error("Ruolo non trovato");
		}
		
		return ruolo;
	}
	
	public RuoloEntity updateRuolo(Long id, RuoloEntity ruoloEntity) {
		RuoloEntity ruolo = ruoloRepository.findById(id).get();
		RuoloEntity updatedRuolo = null;
		
		if (ruolo != null) {
			ruolo.setIdRuolo(ruoloEntity.getIdRuolo());
			ruolo.setNomeRuolo(ruoloEntity.getNomeRuolo());
			
			updatedRuolo = ruoloRepository.save(ruolo);
			ruoloLogger.log.info("Ruolo aggiornato: " + updatedRuolo);
		} else {
			ruoloLogger.log.error("Ruolo non aggiornato");
		}
		
		return updatedRuolo;
	}
	
	public void deleteRuolo(Long id) {
		ruoloRepository.deleteById(id);
	}
}
