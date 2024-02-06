package it.fides.val_training_spring.models.dto;

import java.util.Date;
import java.util.List;

public record ErrorsWithListDto(String message, Date timestamp, List<String> errorList) {
}