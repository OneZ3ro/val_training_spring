package it.fides.val_training_spring.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import it.fides.val_training_spring.models.entities.UtenteEntity;

@Repository
public interface UtenteRepository extends JpaRepository<UtenteEntity, Long> {
	// JPQL
    @Query("SELECT u FROM UtenteEntity u WHERE u.emailUtente = :email")
    UtenteEntity getUtenteByEmail(@Param("email") String email);
	
	// Native
	@Query(value = "SELECT * FROM UtenteEntity WHERE email=?", nativeQuery = true)
	public UtenteEntity getUtenteByPassword(String email);	
}
