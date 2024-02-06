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
import it.fides.val_training_spring.models.entities.ParagrafoEntity;
import it.fides.val_training_spring.services.ParagrafoService;

@RestController
@RequestMapping("/paragrafi")
public class ParagrafoController {

    @Autowired
    private ParagrafoService paragrafoService;
    
    @GetMapping
    public List<ParagrafoEntity> getAllParagrafi() {
        List<ParagrafoEntity> paragrafi = paragrafoService.getAllParagrafi();
        return paragrafi;
    }
    
    @GetMapping("/{id}")
    public ParagrafoEntity getParagrafo(@PathVariable Long id) {
        return paragrafoService.getParagrafo(id);
    }
    
    @PostMapping
    public ParagrafoEntity insertParagrafo(@RequestBody ParagrafoEntity paragrafoEntity) {
    	return paragrafoService.insertParagrafo(paragrafoEntity);
    }
    
    @PutMapping("/{id}")
    public ParagrafoEntity updateParagrafo(@RequestBody ParagrafoEntity paragrafoEntity, @PathVariable Long id) {
    	return paragrafoService.updateParagrafo(id, paragrafoEntity);
    }
    
    @DeleteMapping("/{id}")
    public void deleteParagrafo(@PathVariable Long id) {
    	paragrafoService.deleteParagrafo(id);
    }
}