package it.fides.val_training_spring.utils.loggers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import it.fides.val_training_spring.models.entities.RuoloEntity;

@Component
public class RuoloLogger {
	public final Logger log = LogManager.getLogger(RuoloEntity.class);
}
