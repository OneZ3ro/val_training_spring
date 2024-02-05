package it.fides.val_training_spring.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.fides.val_training_spring.models.entities.ScalaEntity;

@Repository
public interface ScalaRepository extends JpaRepository<ScalaEntity, Long> {

}