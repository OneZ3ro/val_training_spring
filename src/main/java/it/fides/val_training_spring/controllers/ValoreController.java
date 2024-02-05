package it.fides.val_training_spring.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.models.entities.ValoreEntity;
import it.fides.val_training_spring.services.ValoreService;

@RestController
@RequestMapping("/valori")
public class ValoreController {
	
	private static final Logger log = LogManager.getLogger(UtenteEntity.class);
	
    @Autowired
    private ValoreService valoreService;
    
    @GetMapping
    public List<ValoreEntity> getAllValori() {
        
        log.info("tutti valori letti");
        
        return valoreService.getAllValori();
    }
    
    @GetMapping("/{id}")
    public ValoreEntity getValore(@PathVariable Long id) {
    	
    	 log.info("valore dell'id specificato letto");
    	 
        return valoreService.getValore(id);
    }

    @PostMapping
    public void addValore(@RequestBody ValoreEntity valore) {
    	
    	 log.info("valore aggiunto");
    	 
        valoreService.addValore(valore);
    }

    @PutMapping("/{id}")
    public void updateValore(@PathVariable Long id, @RequestBody ValoreEntity valore) {
    	
    	 log.info("valore aggiornato");
    	 
        valoreService.updateValore(id, valore);
    }

    @DeleteMapping("/{id}")
    public void deleteValore(@PathVariable Long id) {
    	
    	 log.info("valore eliminato");
    	 
       valoreService.deleteValore(id);
    }
}
