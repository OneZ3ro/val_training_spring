package it.fides.val_training_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.services.UtenteService;
import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping
    public List<UtenteEntity> getAllUtenti() {
        return utenteService.getAllUtenti();
    }
    
    @GetMapping("/{id}")
    public UtenteEntity getUtente(@PathVariable Long id) {
        return utenteService.getUtente(id);
    }
    
    @PostMapping
    public UtenteEntity insertUtente(@RequestBody UtenteEntity utenteEntity) {
        return utenteService.insertUtente(utenteEntity);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('responsabile')")
    public UtenteEntity updateUtente(@PathVariable Long id, @RequestBody UtenteEntity utenteEntity) {
        return utenteService.updateUtente(id, utenteEntity);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public void deleteUtente(@PathVariable Long id) {
    	utenteService.deleteUtente(id);
    }
    
    @PutMapping("/trash/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public UtenteEntity trashUtente(@PathVariable long id, @RequestBody UtenteEntity utenteEntity) {
    	return utenteService.trashUtente(id, utenteEntity);
    }
}
