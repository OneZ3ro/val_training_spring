package it.fides.val_training_spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import it.fides.val_training_spring.exceptions.ExceptionsHandlerFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	JWTAuthFilter jwtAuthFilter;

	@Autowired
	ExceptionsHandlerFilter exceptionsHandlerFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(form -> form.loginPage("/login"));
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(exceptionsHandlerFilter, JWTAuthFilter.class);
		return http.build();
	}

	@Bean
	PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder(11);
	}
}