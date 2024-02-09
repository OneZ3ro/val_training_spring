package it.fides.val_training_spring.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.fides.val_training_spring.models.dto.ValoreDto;
import it.fides.val_training_spring.models.entities.ParagrafoEntity;
import it.fides.val_training_spring.models.entities.ValoreEntity;
import it.fides.val_training_spring.models.repositories.ValoreRepository;
import it.fides.val_training_spring.utils.converters.ValoreConverter;
import it.fides.val_training_spring.utils.loggers.ValoreLogger;

@Service
public class ValoreService {

    @Autowired
    private ValoreRepository valoreRepository;
    
    @Autowired
    private ValoreLogger valoreLogger;
    
    @Autowired
    private ValoreConverter valoreConverter;

    public List<ValoreEntity> getAllValori() {
        List<ValoreEntity> valori = valoreRepository.findAll();
        
		if (valori.size() > 0) {
			valoreLogger.log.info("Valori: " + valori);
		} else {
			valoreLogger.log.error("Valori non trovati");
		}
		
		return valori;
    }

    public ValoreEntity getValore(Long id) {
        ValoreEntity valore = valoreRepository.findById(id).get();
        
    	if (valore != null) {
    		valoreLogger.log.info("Valore: " + valore);
    	} else {
    		valoreLogger.log.error("Valore non trovato");
    	}
    	
    	return valore;
    }

    public ValoreEntity insertValore(ValoreDto valoreDto) {
    	ValoreEntity valore = valoreConverter.toEntity(valoreDto);
		
    	valore.setDataCreazioneValore(LocalDateTime.now());
    	valore.setDataModificaValore(LocalDateTime.now());
    	valore.setFlgCancellatoValore(false);
    	valoreRepository.save(valore);
    	
    	return valore;
    }

    public ValoreEntity updateValore(Long id, ValoreEntity valoreEntity) {
    	ValoreEntity valore = valoreRepository.findById(id).get();
    	ValoreEntity updatedValore = null;
    	
    	if (valore != null) {
    		valore.setIdValore(valoreEntity.getIdValore());
    		valore.setNomeValore(valoreEntity.getNomeValore());
    		valore.setVotoValore(valoreEntity.getVotoValore());
    		valore.setDataCreazioneValore(valoreEntity.getDataCreazioneValore());
    		valore.setDataModificaValore(valoreEntity.getDataModificaValore());
    		valore.setFlgCancellatoValore(valoreEntity.isFlgCancellatoValore());
    		valore.setParagrafo(valoreEntity.getParagrafo());
    		valore.setUtente(valoreEntity.getUtente());
    		
    		updatedValore = valoreRepository.save(valore);
    		valoreLogger.log.info("Valore aggiornato: " + updatedValore);
    	} else {
    		valoreLogger.log.error("Valore non aggiornato");
    	}

    	return valore;
    }

    public void deleteValore(Long id) {
    	valoreRepository.deleteById(id);
    }
    
    public ValoreEntity trashValore(Long id, ValoreEntity valoreEntity) {
		ValoreEntity valore = valoreRepository.findById(id).get();
		ValoreEntity trashValore = null;
		
		if (valore != null && !valore.isFlgCancellatoValore()) {
			valore.setFlgCancellatoValore(true);
			trashValore = valoreRepository.save(valore);
			valoreLogger.log.info("Valore spostato nel cestino: " + trashValore);
		} else {
			valoreLogger.log.info("Valore non spostato nel cestino");
		}
		return trashValore;
	}
}
