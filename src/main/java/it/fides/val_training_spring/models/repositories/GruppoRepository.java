package it.fides.val_training_spring.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.fides.val_training_spring.models.entities.GruppoEntity;

@Repository
public interface GruppoRepository extends JpaRepository<GruppoEntity, Long> {

}