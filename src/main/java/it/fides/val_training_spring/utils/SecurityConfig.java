package it.fides.val_training_spring.utils;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	String usersByUsernameQuery = "select email, password, not(flg_cancellato) from utente where email = ?";
	String authsByUserQuery = "select u.email, r.id_ruolo from ruolo r LEFT JOIN utente u ON u.id_ruolo = r.id_ruolo where u.email = ?";

	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {

		JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

		userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
		userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);

		return userDetailsManager;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(
				configurer -> configurer.requestMatchers(HttpMethod.GET, "/utenti/all").hasRole("1"));
		// .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("DIPENDENTE")
		// .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("RESPONSABILE")
		// .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("RESPONSABILE")
		// .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN"));
		
		http.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/process-login").permitAll());

		// use HTTP Basic authentication
		http.httpBasic(Customizer.withDefaults());

		// disable Cross Site Request Forgery (CSRF)
		// in general, not required for stateless REST APIs that use POST, PUT, DELETE
		// and/or PATCH
		http.csrf(csrf -> csrf.disable());
		
		

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

	
}