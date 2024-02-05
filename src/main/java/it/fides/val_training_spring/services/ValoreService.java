package it.fides.val_training_spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import it.fides.val_training_spring.models.entities.ValoreEntity;
import it.fides.val_training_spring.models.repositories.ValoreRepository;

@Service
public class ValoreService {

    @Autowired
    private ValoreRepository valoreRepository;

    public List<ValoreEntity> getAllValori() {
        return valoreRepository.findAll();
    }

    public ValoreEntity getValore(Long id) {
        Optional<ValoreEntity> valore = valoreRepository.findById(id);
        if (valore.isPresent()) {
            return valore.get();
        } else {
            return null;
        }
    }

    public ValoreEntity addValore(ValoreEntity valoreEntity) {
    	ValoreEntity valore = valoreRepository.save(valoreEntity);
    	
    	return valore;
    }

    public ValoreEntity updateValore(Long id, ValoreEntity valoreEntity) {
    	
    	ValoreEntity valore = valoreRepository.findById(id).get();
    	
		valore.setNomeValore(valoreEntity.getNomeValore());
		valore.setVotoValore(valoreEntity.getVotoValore());
		valore.setParagrafo(valoreEntity.getParagrafo());
		valore.setUtente(valoreEntity.getUtente());
		
    	valoreRepository.save(valore);
    	
    	return valore;
    }

    public void deleteValore(Long id) {
    	valoreRepository.deleteById(id);
    }
}
