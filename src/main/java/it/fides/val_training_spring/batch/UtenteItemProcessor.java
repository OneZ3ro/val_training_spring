package it.fides.val_training_spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.services.UtenteService;
import it.fides.val_training_spring.services.GruppoService;

public class UtenteItemProcessor implements ItemProcessor<UtenteEntity, UtenteEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtenteItemProcessor.class);

    @Autowired
	UtenteService utenteService;
    
    @Autowired
	GruppoService gruppoService;
    
    @Override
    public UtenteEntity process(final UtenteEntity utente) throws Exception {
    	
    	UtenteEntity utenteEntity = utenteService.findByEmail(utente.getEmailUtente());

        return utenteEntity;
    }

}
