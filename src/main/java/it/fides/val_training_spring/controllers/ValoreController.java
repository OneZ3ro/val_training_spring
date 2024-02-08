package it.fides.val_training_spring.controllers;

import java.util.List;
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
import it.fides.val_training_spring.models.entities.ValoreEntity;
import it.fides.val_training_spring.services.ValoreService;

@RestController
@RequestMapping("/valori")
public class ValoreController {
	
    @Autowired
    private ValoreService valoreService;
    
    @GetMapping
    public List<ValoreEntity> getAllValori() {
        return valoreService.getAllValori();
    }
    
    @GetMapping("/{id}")
    public ValoreEntity getValore(@PathVariable Long id) {
        return valoreService.getValore(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('admin') or hasAuthority('responsabile')")
    public void insertValore(@RequestBody ValoreEntity valore) {
        valoreService.insertValore(valore);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('responsabile')")
    public void updateValore(@PathVariable Long id, @RequestBody ValoreEntity valore) {
        valoreService.updateValore(id, valore);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public void deleteValore(@PathVariable Long id) {
       valoreService.deleteValore(id);
    }
    
    @PutMapping("/trash/{id}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('responsabile')")
	public ValoreEntity trashValore(@PathVariable Long id, @RequestBody ValoreEntity valoreEntity) {
		return valoreService.trashValore(id, valoreEntity);
	}
}
