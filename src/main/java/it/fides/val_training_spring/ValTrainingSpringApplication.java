package it.fides.val_training_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
public class ValTrainingSpringApplication {

	public static void main(String[] args) {		
		SpringApplication.run(ValTrainingSpringApplication.class, args);
	}
}
