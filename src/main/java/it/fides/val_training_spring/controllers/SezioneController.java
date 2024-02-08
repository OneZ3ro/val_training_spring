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
import it.fides.val_training_spring.models.entities.SezioneEntity;
import it.fides.val_training_spring.services.SezioneService;

@RestController
@RequestMapping("/sezioni")
public class SezioneController {

	@Autowired
	private SezioneService sezioneService;

	@GetMapping
	public List<SezioneEntity> getAllSezioni() {
		return sezioneService.getAllSezioni();
	}

	@GetMapping("/{id}")
	public SezioneEntity getSezione(@PathVariable Long id) {
		return sezioneService.getSezione(id);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('admin') or hasAuthority('responsabile')")
	public SezioneEntity insertSezione(@RequestBody SezioneEntity sezioneEntity) {
		return sezioneService.insertSezione(sezioneEntity);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('responsabile')")
	public SezioneEntity updateSezione(@RequestBody SezioneEntity sezioneEntity, @PathVariable Long id) {
		return sezioneService.updateSezione(sezioneEntity, id);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public void deleteSezione(Long id) {
		sezioneService.deleteSezione(id);
	}
	
	@PutMapping("/trash/{id}")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('responsabile')")
	public SezioneEntity trashSezione(@PathVariable Long id, @RequestBody SezioneEntity sezioneEntity) {
		return sezioneService.trashSezione(id, sezioneEntity);
	}
}
