package it.fides.val_training_spring.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fides.val_training_spring.models.dto.SezioneDto;
import it.fides.val_training_spring.models.entities.SezioneEntity;
import it.fides.val_training_spring.models.repositories.SezioneRepository;
import it.fides.val_training_spring.utils.converters.SezioneConverter;
import it.fides.val_training_spring.utils.loggers.SezioneLogger;

@Service
public class SezioneService {

	@Autowired
	private SezioneRepository sezioneRepository;

	@Autowired
	private SezioneLogger sezioneLogger;
	
	@Autowired
	private SezioneConverter sezioneConverter;

	public List<SezioneEntity> getAllSezioni() {
		List<SezioneEntity> sezioni = sezioneRepository.findAll();
		
		if (sezioni.size() > 0) {
			sezioneLogger.log.info("Scale: " + sezioni);
		} else {
			sezioneLogger.log.error("Scale non trovate");
		}
		
		return sezioni;
	}

	public SezioneEntity getSezione(Long id) {
		SezioneEntity sezione = sezioneRepository.findById(id).get();
		
		if (sezione != null) {
			sezioneLogger.log.info("Sezione: " + sezione);
		} else {
			sezioneLogger.log.error("Sezione non trovata");
		}

		return sezione;

	}

	public SezioneEntity insertSezione(SezioneDto sezioneDto) {
		SezioneEntity sezione = sezioneConverter.toEntity(sezioneDto);
		
		sezione.setDataCreazioneSezione(LocalDateTime.now());
		sezione.setDataModificaSezione(LocalDateTime.now());
		sezione.setFlgCancellatoSezione(false);
		sezioneRepository.save(sezione);

		if (sezione != null) {
			sezioneLogger.log.info("Sezione: " + sezione);
		}
		
		return sezione;
	}
	
	public SezioneEntity updateSezione(SezioneEntity sezioneEntity, Long id) {
		SezioneEntity sezione = sezioneRepository.findById(id).get();
		SezioneEntity updatedSezione = null;
		
		if (sezione != null) {
			sezione.setIdSezione(sezioneEntity.getIdSezione());
			sezione.setTitoloSezione(sezioneEntity.getTitoloSezione());
			sezione.setDataCreazioneSezione(sezioneEntity.getDataCreazioneSezione());
			sezione.setDataModificaSezione(sezioneEntity.getDataModificaSezione());
			sezione.setFlgCancellatoSezione(sezioneEntity.isFlgCancellatoSezione());
			
			updatedSezione = sezioneRepository.save(sezione);
			sezioneLogger.log.info("Sezione aggiornata: " + updatedSezione);
		} else {
			sezioneLogger.log.error("Sezione non aggiornata");
		}
		
		return updatedSezione;
	}
	
	public void deleteSezione(Long id) {
		sezioneRepository.deleteById(id);
	}
	
	public SezioneEntity trashSezione(Long id, SezioneEntity sezioneEntity) {
		SezioneEntity sezione = sezioneRepository.findById(id).get();
		SezioneEntity trashSezione = null;
		
		if (sezione != null && !sezione.isFlgCancellatoSezione()) {
			sezione.setFlgCancellatoSezione(true);
			trashSezione = sezioneRepository.save(sezione);
			sezioneLogger.log.info("Sezione spostata nel cestino: " + trashSezione);
		} else {
			sezioneLogger.log.info("Sezione non spostata nel cestino");
		}
		return trashSezione;
	}
}
