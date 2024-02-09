package it.fides.val_training_spring.models.dto;

import java.util.Date;


public record ErrorsDto(String message, Date timestamp) {
}
