package it.fides.val_training_spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fides.val_training_spring.models.entities.SezioneEntity;
import it.fides.val_training_spring.models.repositories.SezioneRepository;
import it.fides.val_training_spring.utils.logger.SezioneLogger;

@Service
public class SezioneService {

	@Autowired
	private SezioneRepository sezioneRepository;

	@Autowired
	private SezioneLogger sezioneLogger;

	public List<SezioneEntity> getAllSezione() {

		List<SezioneEntity> sezioni = sezioneRepository.findAll();
		sezioneLogger.log.info("");
		sezioneLogger.log.warn("");

		return sezioni;
	}

	public SezioneEntity getSezione(Long id) {
		SezioneEntity sezione = sezioneRepository.findById(id).get();
		sezioneLogger.log.info("");
		sezioneLogger.log.warn("");

		return sezione;

	}

	public SezioneEntity createSezione(SezioneEntity sezioneEntity) {
		SezioneEntity sezione = sezioneRepository.save(sezioneEntity);
		sezioneLogger.log.info("");
		sezioneLogger.log.warn("");
		
		return sezione;
	}
	
	public SezioneEntity updateSezione(SezioneEntity sezioneEntity, Long id) {
		SezioneEntity sezione = sezioneRepository.findById(id).get();
		
		sezione.setTitolo(sezioneEntity.getTitolo());
		sezione.setIdSezione(sezioneEntity.getIdSezione());
		sezione.setDataCreazione(sezioneEntity.getDataCreazione());
		sezione.setDataModifica(sezioneEntity.getDataModifica());
		sezione.setFlgCancellato(sezioneEntity.isFlgCancellato());
		
		sezioneLogger.log.info("");
		sezioneLogger.log.warn("");
		
		sezioneRepository.save(sezione);
		
		return sezione;
		
	}
	
	public void deleteSezione(Long id) {
		sezioneRepository.deleteById(id);
		
		sezioneLogger.log.info("");
		sezioneLogger.log.warn("");
	}

}
