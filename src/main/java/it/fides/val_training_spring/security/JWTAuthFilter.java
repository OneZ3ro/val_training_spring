package it.fides.val_training_spring.security;

import it.fides.val_training_spring.exceptions.UnauthorizedException;
import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.services.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
	@Autowired
	private JWTTools jwtTools;
	@Autowired
	private UtenteService usersService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = getJwtFromRequest(request);
		if (token == null) {
			throw new UnauthorizedException("Per favore passa il Bearer Token nell'Authorization header");
		} else {
			
			System.out.println("TOKEN -> " + token);
			jwtTools.verifyToken(token);
			String id = jwtTools.extractIdFromToken(token);
			UtenteEntity currentUtente = usersService.findById(Long.parseLong(id));
			//System.out.println(currentUtente.toString());

			Authentication authentication = new UsernamePasswordAuthenticationToken(currentUtente, null,
					currentUtente.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			System.out.println(authentication.toString());
			filterChain.doFilter(request, response);
		}

	}
	
	private String getJwtFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                	return cookie.getValue();
                }
            }
        }
        return null;
    }

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

		return new AntPathMatcher().match("/auth/**", request.getServletPath())
				|| new AntPathMatcher().match("/login", request.getServletPath());
	}
}
