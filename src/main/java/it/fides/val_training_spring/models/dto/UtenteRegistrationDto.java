package it.fides.val_training_spring.models.dto;

import jakarta.validation.constraints.NotEmpty;

public record UtenteRegistrationDto(
        @NotEmpty(message = "L'email è un campo obbligatorio!")
        String email,
        @NotEmpty(message = "La password è un campo obbligatorio!")
        String password,
        @NotEmpty(message = "Il nome è un campo obbligatorio!")
        String nome,
        @NotEmpty(message = "Il cognome è un campo obbligatorio!")
        String cognome,
        String informazioniGenerali
){}