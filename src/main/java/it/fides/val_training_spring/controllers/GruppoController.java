package it.fides.val_training_spring.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.fides.val_training_spring.models.entities.GruppoEntity;
import it.fides.val_training_spring.services.GruppoService;

@RestController
@RequestMapping("/gruppi")
public class GruppoController {
	
	@Autowired
	private GruppoService gruppoService;
	
	@GetMapping
	public List<GruppoEntity> getAllGruppi() {
		return gruppoService.getAllGruppi();
	}
	
	@GetMapping("/{id}")
	public GruppoEntity getGruppo(@PathVariable Long id) {
		return gruppoService.getGruppo(id);
	}
	
	@PostMapping
	public GruppoEntity insertGruppo(@RequestBody GruppoEntity gruppoEntity) {
		return gruppoService.insertGruppo(gruppoEntity);
	}
	
	@PutMapping("/{id}")
	public GruppoEntity updateGruppo(@PathVariable Long id, @RequestBody GruppoEntity gruppoEntity) {
		return gruppoService.updateGruppo(id, gruppoEntity);
	}
	
	@DeleteMapping("/{id}")
	public void deleteGruppo(@PathVariable Long id) {
		gruppoService.deleteGruppo(id);
	}
}
