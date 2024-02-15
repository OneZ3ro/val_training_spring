package it.fides.val_training_spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import it.fides.val_training_spring.models.dto.Person;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Person process(final Person person) throws Exception {
        String nome = person.getNome().toUpperCase();
        String cognome = person.getCognome().toUpperCase();

        Person transformedPerson = new Person(nome, cognome);
        LOGGER.info("Converting ( {} ) into ( {} )", person, transformedPerson);

        return transformedPerson;
    }
}
