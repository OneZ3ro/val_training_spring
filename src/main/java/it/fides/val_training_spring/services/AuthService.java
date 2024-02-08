package it.fides.val_training_spring.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.io.IOException;
import it.fides.val_training_spring.exceptions.BadRequestException;
import it.fides.val_training_spring.exceptions.UnauthorizedException;
import it.fides.val_training_spring.models.dto.UtenteLoginDto;
import it.fides.val_training_spring.models.dto.UtenteRegistrationDto;
import it.fides.val_training_spring.models.dto.UtenteUpdateDto;
import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.models.repositories.UtenteRepository;
import it.fides.val_training_spring.security.JWTTools;
import it.fides.val_training_spring.utils.loggers.UtenteLogger;

@Service
public class AuthService {
	@Autowired
	private UtenteService utenteService;
	
	@Autowired
    private UtenteRepository utenteRepository;

	@Autowired
	private JWTTools jwtTools;

	@Autowired
	private PasswordEncoder bcrypt;
	
	@Autowired
	private RuoloService ruoloService;
	
	@Autowired
	private UtenteLogger utenteLogger;

	public String authenticateUser(UtenteLoginDto body) throws Exception {
		UtenteEntity user = utenteService.findByEmail(body.email());
		if (bcrypt.matches(body.password(), user.getPassword())) {
			return jwtTools.createToken(user);
		} else {
			throw new UnauthorizedException("Credenziali non valide!");
		}
	}
	
	public UtenteEntity registerUser(UtenteRegistrationDto body) throws IOException {
		utenteRepository.findByEmailUtente(body.email()).ifPresent( user -> {
            throw new BadRequestException("L'email " + user.getEmailUtente() + " è già utilizzata!");
        });
        UtenteEntity newUser = new UtenteEntity();
        newUser.setPasswordUtente(bcrypt.encode(body.password()));
        newUser.setEmailUtente(body.email());
        newUser.setNomeUtente(body.nome());
        newUser.setCognomeUtente(body.cognome());
        newUser.setRuolo(ruoloService.getRuolo(1L));
        newUser.setDataCreazioneUtente(LocalDateTime.now());
        newUser.setDataModificaUtente(LocalDateTime.now());
        newUser.setFlgCancellatoUtente(false);
        if(body.informazioniGenerali() != null) {
        	newUser.setInformazioniGeneraliUtente(body.informazioniGenerali());
		} else {
			newUser.setInformazioniGeneraliUtente("Lorem Ipsum");
		}
        
        utenteRepository.save(newUser);

        return newUser;
    }
	
	
	public UtenteEntity updateUtenteById(Long id, UtenteUpdateDto body) {
    	UtenteEntity utenteFound = utenteService.findById(id);
    	
    	if (utenteFound != null) {
    		utenteLogger.log.info("Utente: " + utenteFound);
    		
    		if (!bcrypt.matches(body.password(), utenteFound.getPassword())) {
    			utenteFound.setPasswordUtente(bcrypt.encode(body.password()));
    		}
    		
    		if(!utenteFound.getEmailUtente().toLowerCase().equals(body.email().toLowerCase())) {
    			utenteFound.setEmailUtente(body.email());
    		}
    		
    		if(!utenteFound.getNomeUtente().toLowerCase().equals(body.nome().toLowerCase())) {
    			utenteFound.setNomeUtente(body.nome());
    		}
    		
    		if(!utenteFound.getCognomeUtente().toLowerCase().equals(body.cognome().toLowerCase())) {
    			utenteFound.setCognomeUtente(body.cognome());
    		}

    		utenteFound.setDataModificaUtente(LocalDateTime.now());
    		
    		if(!body.informazioniGenerali().isBlank()) {
    			utenteFound.setInformazioniGeneraliUtente(body.informazioniGenerali());
    		}
            
    		utenteLogger.log.info("Utente aggiornato: " + utenteFound);
    	} else {
    		utenteLogger.log.error("Utente non aggiornato");
    	}
    	
    	return utenteRepository.save(utenteFound);
    	
    }

}