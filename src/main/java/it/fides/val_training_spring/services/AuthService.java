package it.fides.val_training_spring.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.io.IOException;
import it.fides.val_training_spring.exceptions.BadRequestException;
import it.fides.val_training_spring.exceptions.UnauthorizedException;
import it.fides.val_training_spring.models.dto.UtenteDto;
import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.models.repositories.UtenteRepository;
import it.fides.val_training_spring.security.JWTTools;


@Service
public class AuthService {
	@Autowired
	private UtenteService usersService;
	
	@Autowired
    private UtenteRepository utenteRepository;

	@Autowired
	private JWTTools jwtTools;

	@Autowired
	private PasswordEncoder bcrypt;
	
	@Autowired
	private RuoloService ruoloService;

	public String authenticateUser(UtenteDto body) throws Exception {
		// 1. Verifichiamo che l'email dell'utente sia nel db
		UtenteEntity user = usersService.findByEmail(body.getEmailUtente());
		// 2. In caso affermativo, verifichiamo se la password corrisponde a quella
		// trovata nel db
		if (bcrypt.matches(body.getPasswordUtente(), user.getPassword())) {
			// 3. Se le credenziali sono OK --> Genero un JWT e lo restituisco
			return jwtTools.createToken(user);
		} else {
			// 4. Se le credenziali NON sono OK --> 401
			throw new UnauthorizedException("Credenziali non valide!");
		}
	}
	
	
	public UtenteEntity registerUser(UtenteDto body) throws IOException {

        // verifico se l'email è già utilizzata
		utenteRepository.findByEmailUtente(body.getEmailUtente()).ifPresent( user -> {
            throw new BadRequestException("L'email " + user.getEmailUtente() + " è già utilizzata!");
        });
        UtenteEntity newUser = new UtenteEntity();
        newUser.setPasswordUtente(bcrypt.encode(body.getEmailUtente()));
        newUser.setEmailUtente(body.getEmailUtente());
        newUser.setNomeUtente(body.getNomeUtente());
        newUser.setCognomeUtente(body.getCognomeUtente());
        newUser.setRuolo(ruoloService.getRuolo(1L));
        newUser.setDataCreazioneUtente(LocalDateTime.now());
        newUser.setDataModificaUtente(LocalDateTime.now());
        newUser.setFlgCancellatoUtente(false);
        newUser.setInformazioniGeneraliUtente("Lorem Ipsum");
        
        utenteRepository.save(newUser);

        return newUser;
    }
}