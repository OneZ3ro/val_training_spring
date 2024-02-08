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

import it.fides.val_training_spring.models.dto.CreazioneGruppoDto;
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
	@PreAuthorize("hasAuthority('admin') or hasAuthority('responsabile')")
	public GruppoEntity insertGruppo(@RequestBody CreazioneGruppoDto gruppoEntity) {
		return gruppoService.insertGruppo(gruppoEntity);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('responsabile')")
	public GruppoEntity updateGruppo(@PathVariable Long id, @RequestBody GruppoEntity gruppoEntity) {
		return gruppoService.updateGruppo(id, gruppoEntity);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public void deleteGruppo(@PathVariable Long id) {
		gruppoService.deleteGruppo(id);
	}
	
	@PutMapping("/trash/{id}")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('responsabile')")
	public GruppoEntity trashGruppo(@PathVariable Long id, @RequestBody GruppoEntity gruppoEntity) {
		return gruppoService.trashGruppo(id, gruppoEntity);
	}
}
