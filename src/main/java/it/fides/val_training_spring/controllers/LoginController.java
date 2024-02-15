package it.fides.val_training_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/index")
    public String index() {
    	return "index";
    }
    
    @GetMapping("/menu")
    public String menu() {
    	return "/menu";
    }
}