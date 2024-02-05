package it.fides.val_training_spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.fides.val_training_spring.models.entities.ParagrafoEntity;
import it.fides.val_training_spring.models.repositories.ParagrafoRepository;
import it.fides.val_training_spring.service.ParagrafoService;
@RestController
public class ParagrafoController {

    @Autowired
    private ParagrafoService paragrafoService;
    
    @Autowired
    private ParagrafoRepository paragrafoRepository;
    
    @GetMapping("/paragrafi")
    public List<ParagrafoEntity> geAllParagrafi() {
        List<ParagrafoEntity> paragrafi = paragrafoService.getAllParagrafi();
        return paragrafi;
    }
    
    @GetMapping("/paragrafi/{id}")
    public Optional<ParagrafoEntity> getParagrafiById(@PathVariable Long id) {
        return paragrafoService.getParagrafoById(id);
    }
    
    @PostMapping
    public ParagrafoEntity createParagrafo(@RequestBody ParagrafoEntity paragrafoEntity) {
    	return paragrafoRepository.save(paragrafoEntity);
    }
    
    @PutMapping("/paragrafi/{id}")
    public ParagrafoEntity updateParagrafo(@RequestBody ParagrafoEntity paragrafoEntity, @PathVariable Long id) {
    	return paragrafoService.updateParagrafo(paragrafoEntity, id);
    }
    
    @DeleteMapping("/paragrafi/{id}")
    public void deleteParagrafo(@RequestBody ParagrafoEntity paragrafoEntity) {
    	paragrafoRepository.delete(paragrafoEntity);
    }

}