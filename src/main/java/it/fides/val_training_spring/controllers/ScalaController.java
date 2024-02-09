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

import it.fides.val_training_spring.models.dto.ScalaDto;
import it.fides.val_training_spring.models.entities.ScalaEntity;
import it.fides.val_training_spring.models.entities.ValoreEntity;
import it.fides.val_training_spring.services.ScalaService;

@RestController
@RequestMapping("/scale")
public class ScalaController {

	@Autowired
	private ScalaService scalaService;

	@GetMapping
	public List<ScalaEntity> getAllScale() {
		return scalaService.getAllScale();
	}
	
	@GetMapping("/{id}")
	public ScalaEntity getScala(@PathVariable Long id) {
		return scalaService.getScala(id);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('admin') or hasAuthority('responsabile')")
	public ScalaEntity insertScala(@RequestBody ScalaDto scalaDto) {
		return scalaService.insertScala(scalaDto);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('responsabile')")
	public ScalaEntity updateScala(@RequestBody ScalaEntity scalaEntity, @PathVariable Long id) {
		return scalaService.updateScala(scalaEntity, id);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('admin')")
	public void deleteScala(Long id) {
		scalaService.deleteScala(id);
	}
	
	@PutMapping("/trash/{id}")
	@PreAuthorize("hasAuthority('admin') or hasAuthority('responsabile')")
	public ScalaEntity trashScala(@PathVariable Long id, @RequestBody ScalaEntity scalaEntity) {
		return scalaService.trashScala(id, scalaEntity);
	}
}
