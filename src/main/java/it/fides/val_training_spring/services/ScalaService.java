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
		scalaLogger.log.info("");
		scalaLogger.log.warn("");

		return scale;
	}

	public ScalaEntity getScala(Long id) {

		ScalaEntity scala = scalaRepository.findById(id).get();
		scalaLogger.log.info("");
		scalaLogger.log.warn("");

		return scala;
	}

	public ScalaEntity createScala(ScalaEntity scalaEntity) {

		ScalaEntity scala = scalaRepository.save(scalaEntity);
		scalaLogger.log.info("");
		scalaLogger.log.warn("");

		return scala;
	}
	
	public ScalaEntity updateScala(ScalaEntity scalaEntity, Long id) {
		ScalaEntity scala = scalaRepository.findById(id).get();
		scala.setTitoloScala(scalaEntity.getTitoloScala());
		scala.setDescrizioneScala(scalaEntity.getDescrizioneScala());
		scala.setDataCreazioneScala(scalaEntity.getDataCreazioneScala());
		scala.setDataModificaScala(scalaEntity.getDataCreazioneScala());
		scala.setFlgCancellatoScala(scalaEntity.isFlgCancellatoScala());
		scala.setParagrafo(scalaEntity.getParagrafo());
		
		scalaLogger.log.info("");
		scalaLogger.log.warn("");

		
		scalaRepository.save(scala);
		
		return scala;
	}

	public void deleteScala(Long id) {
		scalaRepository.deleteById(id);
		scalaLogger.log.info("");
		scalaLogger.log.warn("");

	}

}
