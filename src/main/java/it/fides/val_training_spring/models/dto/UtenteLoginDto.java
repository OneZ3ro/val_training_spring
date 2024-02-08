package it.fides.val_training_spring.models.dto;

import java.util.List;

public record UtenteLoginDto(
        String email,
        String password,
        String nome,
        String cognome,
        String informazioniGenerali,
        List<Long> gruppiId,
        Long ruolo,
        boolean flgCancellato
){}