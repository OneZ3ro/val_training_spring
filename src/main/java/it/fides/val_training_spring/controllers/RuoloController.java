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

import it.fides.val_training_spring.models.entities.RuoloEntity;
import it.fides.val_training_spring.models.entities.ValoreEntity;
import it.fides.val_training_spring.services.RuoloService;

@RestController
@RequestMapping("/ruoli")
public class RuoloController {
	
	@Autowired
	private RuoloService ruoloService;
	
	@GetMapping
	public List<RuoloEntity> getAllRuoli() {
		return ruoloService.getAllRuoli();
	}
	
	@GetMapping("/{id}")
	public RuoloEntity getRuolo(@PathVariable Long id) {
		return ruoloService.getRuolo(id);
	}
	
	@PostMapping
    @PreAuthorize("hasAuthority('admin')")
	public RuoloEntity insertRuolo(@RequestBody RuoloEntity ruoloEntity) {
		return ruoloService.insertRuolo(ruoloEntity);
	}
	
	@PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
	public RuoloEntity updateRuolo(@PathVariable Long id, @RequestBody RuoloEntity ruoloEntity) {
		return ruoloService.updateRuolo(id, ruoloEntity);
	}
	
	@DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
	public void deleteRuolo(@PathVariable Long id) {
		ruoloService.deleteRuolo(id);
	}
}
