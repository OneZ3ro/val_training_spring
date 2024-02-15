package it.fides.val_training_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.io.IOException;
import it.fides.val_training_spring.exceptions.BadRequestException;
import it.fides.val_training_spring.models.dto.UtenteDto;
import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(path="/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpServletResponse response) throws Exception {
    	//creo DTO con email e password e lo passo all' authenticate
    	UtenteDto utenteDto = new UtenteDto();
    	utenteDto.setEmailUtente(email);
    	utenteDto.setPasswordUtente(password);
   
    	//imposto cookie
    	Cookie cookie = new Cookie("jwt", authService.authenticateUser(utenteDto));
    	cookie.setPath("/"); // Set the cookie's path
        cookie.setHttpOnly(true); // Make sure the cookie is accessible only through HTTP (not JavaScript)
        cookie.setMaxAge(3600); // Set the expiration time of the cookie (in seconds)
    	response.addCookie(cookie);
    	
    	//authService.authenticateUser(utenteDto) ritorna il token
    	return "redirect:/menu";
    }
    
    
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public UtenteEntity saveUser(@RequestBody @Validated UtenteDto body, BindingResult validation){
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