package it.fides.val_training_spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import it.fides.val_training_spring.models.dto.ErrorsWithListDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ErrorsWithListDto handleBadRequest(BadRequestException e) {
        if (e.getErrorList() != null) {
            List<String> errorsList = e.getErrorList().stream().map(objectError -> objectError.getDefaultMessage()).toList();
            return new ErrorsWithListDto(e.getMessage(), new Date(), errorsList);
        } else {
            return new ErrorsWithListDto(e.getMessage(), new Date(), new ArrayList<>());
        }

    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    public ErrorsWithListDto handleUnauthorized(UnauthorizedException e) {
        return new ErrorsWithListDto(e.getMessage(), new Date(), null);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    public ErrorsWithListDto handleAccessDenied(AccessDeniedException e) {
        return new ErrorsWithListDto(e.getMessage(), new Date(), null);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    public ErrorsWithListDto handleNotFound(NotFoundException e) {
        return new ErrorsWithListDto(e.getMessage(), new Date(), null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    public ErrorsWithListDto handleGeneric(Exception e) {
        e.printStackTrace();
        return new ErrorsWithListDto("Problema lato server (Internal Server Error)", new Date(), null);
    }
}