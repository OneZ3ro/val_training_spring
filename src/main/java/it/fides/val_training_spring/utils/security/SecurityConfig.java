package it.fides.val_training_spring.utils.security;

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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers(HttpMethod.POST, "/signup").permitAll()
                .requestMatchers(HttpMethod.GET, "/utenti/all").hasAnyAuthority("ROLE_responsabile")
                .requestMatchers(HttpMethod.GET, "/utenti/{id}").hasAnyAuthority("ROLE_dipendente", "ROLE_responsabile")
                .requestMatchers(HttpMethod.GET, "/home").hasAnyAuthority("1", "2")
                .anyRequest().authenticated()
        		);

       /* http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/process-login")
                .defaultSuccessUrl("/home", true)
                .permitAll());*/

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());
       
        return http.build();
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}