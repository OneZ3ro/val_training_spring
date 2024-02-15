package it.fides.val_training_spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import it.fides.val_training_spring.models.entities.UtenteEntity;

public class UtenteItemProcessor implements ItemProcessor<UtenteEntity, UtenteEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UtenteItemProcessor.class);

    @Override
    public UtenteEntity process(final UtenteEntity utente) {
        String nomeUtente = utente.getNomeUtente().toUpperCase();
        String cognomeUtente = utente.getCognomeUtente().toUpperCase();
        String emailUtente = utente.getEmailUtente().toUpperCase();

        UtenteEntity transformedUtente = new UtenteEntity(nomeUtente, cognomeUtente, emailUtente);
        LOGGER.info("Converting ( {} ) into ( {} )", utente, transformedUtente);

        return transformedUtente;
    }

}
