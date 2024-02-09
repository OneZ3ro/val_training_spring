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

		return Jwts.builder().setSubject(String.valueOf(user.getIdUtente()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();

	}

	public void verifyToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
		} catch (Exception ex) {
			throw new UnauthorizedException("Il token non è valido! Per favore effettua nuovamente il login!");
		}

	}

	public String extractIdFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token)
				.getBody().getSubject();
	}
}
