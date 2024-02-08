package it.fides.val_training_spring.models.dto;

import jakarta.validation.constraints.NotEmpty;

public record CreazioneGruppoDto(
        @NotEmpty(message = "Il nome è un campo obbligatorio!")
        String nomeGruppo,
        @NotEmpty(message = "Il cognome è un campo obbligatorio!")
        Long responsabile
){}