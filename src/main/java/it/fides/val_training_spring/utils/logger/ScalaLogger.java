package it.fides.val_training_spring.utils.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import it.fides.val_training_spring.models.entities.ScalaEntity;


@Component
public class ScalaLogger {

	public final Logger log = LogManager.getLogger(ScalaEntity.class);
}
