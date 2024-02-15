package it.fides.val_training_spring.services;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.models.repositories.UtenteRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UtenteRepository repository;

  public UserDetailsServiceImpl(UtenteRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) {

    UtenteEntity user = null;
	try {
		user = repository.findByEmailUtente(email).orElseThrow(() -> new NotFoundException());
	} catch (NotFoundException e) {
		e.printStackTrace();
	}

    return User.builder()
        .username(user.getEmailUtente())
        .password(user.getPasswordUtente())
        .build();
  }
}