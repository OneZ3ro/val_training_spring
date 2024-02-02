package it.fides.val_training_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.fides.val_training_spring.entities.ParagrafoEntity;

@Repository
public interface ParagrafoRepository extends JpaRepository<ParagrafoEntity, Long> {

}
