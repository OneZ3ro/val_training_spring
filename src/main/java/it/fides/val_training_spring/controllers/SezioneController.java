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

import it.fides.val_training_spring.models.entities.SezioneEntity;
import it.fides.val_training_spring.services.SezioneService;

@RestController
@RequestMapping("/sezione")
public class SezioneController {

	@Autowired
	private SezioneService sezioneService;

	@GetMapping
	public List<SezioneEntity> getAllSezione() {
		return sezioneService.getAllSezione();
	}

	@GetMapping("/{id}")
	public SezioneEntity getSezione(@PathVariable Long id) {
		return sezioneService.getSezione(id);
	}

	@PostMapping
	public SezioneEntity createSezione(@RequestBody SezioneEntity sezioneEntity) {
		return sezioneService.createSezione(sezioneEntity);
	}
	
	@PutMapping("/{id}")
	public SezioneEntity updateSezione(@RequestBody SezioneEntity sezioneEntity, @PathVariable Long id) {
		return sezioneService.updateSezione(sezioneEntity, id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteSezione(Long id) {
		sezioneService.deleteSezione(id);
		
	}
}
