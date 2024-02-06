package it.fides.val_training_spring.utils.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import it.fides.val_training_spring.models.entities.SezioneEntity;

@Component
public class SezioneLogger {

	public final Logger log = LogManager.getLogger(SezioneEntity.class);
}
