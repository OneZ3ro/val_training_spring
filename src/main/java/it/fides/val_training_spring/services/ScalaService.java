package it.fides.val_training_spring.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.fides.val_training_spring.models.entities.ScalaEntity;
import it.fides.val_training_spring.models.repositories.ScalaRepository;
import it.fides.val_training_spring.utils.logger.ScalaLogger;

@Service
public class ScalaService {

	@Autowired
	private ScalaRepository scalaRepository;

	@Autowired
	private ScalaLogger scalaLogger;

	public List<ScalaEntity> getAllScale() {

		List<ScalaEntity> scale = scalaRepository.findAll();
		if (scale.size() > 0) {
			scalaLogger.log.info("Scale: " + scale);
		} else {
			scalaLogger.log.error("Scale non trovate");
		}
		
		return scale;
	}

	public ScalaEntity getScala(Long id) {
		ScalaEntity scala = scalaRepository.findById(id).get();
		
		if (scala != null) {
			scalaLogger.log.info("Scala: " + scala);
		} else {
			scalaLogger.log.error("Scala non trovata");
		}

		return scala;
	}

	public ScalaEntity insertScala(ScalaEntity scalaEntity) {
		ScalaEntity scala = scalaRepository.save(scalaEntity);
		
		if (scala != null) {
			scalaLogger.log.info("Scala: " + scala);
		} else {
			scalaLogger.log.error("Scala non creata");
		}

		return scala;
	}
	
	public ScalaEntity updateScala(ScalaEntity scalaEntity, Long id) {
		ScalaEntity scala = scalaRepository.findById(id).get();
		ScalaEntity updatedScala = null;
		
		if (scala != null) {		
			scala.setIdScala(scalaEntity.getIdScala());
			scala.setTitoloScala(scalaEntity.getTitoloScala());
			scala.setDescrizioneScala(scalaEntity.getDescrizioneScala());
			scala.setDataCreazioneScala(scalaEntity.getDataCreazioneScala());
			scala.setDataModificaScala(scalaEntity.getDataCreazioneScala());
			scala.setFlgCancellatoScala(scalaEntity.isFlgCancellatoScala());
			scala.setParagrafo(scalaEntity.getParagrafo());
			
			updatedScala = scalaRepository.save(scala);
			scalaLogger.log.info("Scala aggiornata: " + updatedScala);
		} else {
			scalaLogger.log.error("Scala non aggiornata");
		}		
		
		return updatedScala;
	}

	public void deleteScala(Long id) {
		scalaRepository.deleteById(id);
	}
}
