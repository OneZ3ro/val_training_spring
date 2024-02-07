package it.fides.val_training_spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
	
	String usersByUsernameQuery = "select email, password, NOT(flg_cancellato) from utente where email = ?";
    String authsByUserQuery = "select u.email, r.nome from ruolo r LEFT JOIN utente u ON u.id_ruolo = r.id_ruolo where u.email = ?";
    
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

    	JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        
        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
        
        return userDetailsManager;
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	http.authorizeHttpRequests(configurer ->
                    configurer
                    		.requestMatchers(HttpMethod.GET, "/utenti").hasAuthority("responsabile")
                    		.requestMatchers(HttpMethod.GET, "/paragrafo").hasAuthority("responsabile")
                    		.anyRequest().authenticated()
        );
        
        http.formLogin(form ->
			        form
			                .loginPage("/login")
			                .loginProcessingUrl("/processing-login")
			                .defaultSuccessUrl("/index.html")
			                .permitAll()
        );
        

        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
    
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}