package it.fides.val_training_spring.utils.loggers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import it.fides.val_training_spring.models.entities.ParagrafoEntity;

@Component
public class ParagrafoLogger {
	public final Logger log = LogManager.getLogger(ParagrafoEntity.class);
}
