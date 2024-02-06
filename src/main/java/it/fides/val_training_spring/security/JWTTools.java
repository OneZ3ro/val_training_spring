package it.fides.val_training_spring.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.fides.val_training_spring.exceptions.UnauthorizedException;
import it.fides.val_training_spring.models.entities.UtenteEntity;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {
	private String secret = "kdsjhfoi23434f-sdfsfsfs-skfjweweretpopmasd";

	public String createToken(UtenteEntity user) {

		return Jwts.builder().setSubject(String.valueOf(user.getIdUtente()))// Subject <-- A chi appartiene il token
				.setIssuedAt(new Date(System.currentTimeMillis())) // Data di emissione (IAT - Issued At)
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Data di scadenza (Expiration
																						// Date)
				.signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();

	} // Si utilizza al login

	public void verifyToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
		} catch (Exception ex) {
			throw new UnauthorizedException("Il token non è valido! Per favore effettua nuovamente il login!");
		}

	} // Si utilizza in tutte le request autenticate

	public String extractIdFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token)
				.getBody().getSubject();
		// Nel body (payload) del token ci sono il subject( che è l'ID dell'utente), la
		// data di emissione, e la data di scadenza.
		// A noi interessa l'id dell'utente
	}
}