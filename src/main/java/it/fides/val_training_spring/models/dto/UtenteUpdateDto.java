package it.fides.val_training_spring.models.dto;

import java.util.List;

public record UtenteUpdateDto(
        String email,
        String password,
        String nome,
        String cognome,
        String informazioniGenerali,
        List<Long> gruppiId,
        Long ruolo,
        boolean flgCancellato
){}