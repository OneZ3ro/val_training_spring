package it.fides.val_training_spring.models.dto;

public record UtenteUpdateDto(
        String email,
        String password,
        String nome,
        String cognome,
        String informazioniGenerali
){}