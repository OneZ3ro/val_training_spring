package it.fides.val_training_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.io.IOException;
import it.fides.val_training_spring.exceptions.BadRequestException;
import it.fides.val_training_spring.models.dto.UtenteLoginDto;
import it.fides.val_training_spring.models.dto.UtenteLoginSuccessDto;
import it.fides.val_training_spring.models.dto.UtenteRegistrationDto;
import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.services.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UtenteLoginSuccessDto login(@RequestBody UtenteLoginDto body) throws Exception {

        return new UtenteLoginSuccessDto(authService.authenticateUser(body));
    }
    
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public UtenteEntity saveUser(@RequestBody @Validated UtenteRegistrationDto body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.registerUser(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
