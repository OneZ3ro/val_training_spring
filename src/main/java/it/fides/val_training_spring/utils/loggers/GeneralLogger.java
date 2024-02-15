package it.fides.val_training_spring.utils.loggers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class GeneralLogger {
	public final Logger log = LogManager.getLogger(String.class);

}
