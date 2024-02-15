package it.fides.val_training_spring.services;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import it.fides.val_training_spring.exceptions.NotFoundException;
import it.fides.val_training_spring.models.dto.UtenteUpdateDto;
import it.fides.val_training_spring.models.entities.GruppoEntity;
import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.models.repositories.UtenteRepository;
import it.fides.val_training_spring.utils.loggers.UtenteLogger;

@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Autowired
	private UtenteLogger utenteLogger;
	
	@Autowired
	private GruppoService gruppoService;
	
	@Autowired
	private RuoloService ruoloService;
	
	@Autowired
	private EmailService emailService;

    public List<UtenteEntity> getAllUtenti() {        
        List<UtenteEntity> utenti = utenteRepository.findAll();

        if (utenti.size() > 0) {
            utenteLogger.log.info("Utenti: " + utenti);
        } else {
            utenteLogger.log.error("Utenti non trovati");
        }
        
        return utenti;
    }
    
    public UtenteEntity getUtente(Long id) {
    	UtenteEntity utente = utenteRepository.findById(id).get();
    	
    	if (utente != null) {
    		utenteLogger.log.info("Utente: " + utente);
    	} else {
    		utenteLogger.log.error("Utente non trovato");
    	}
    	
    	return utente;
    }
    
    public UtenteEntity insertUtente(UtenteEntity utenteEntity) {
    	UtenteEntity utente = utenteRepository.save(utenteEntity);
    	if (utente != null) {
    		utenteLogger.log.info("Utente: " + utente);
    	} else {
    		utenteLogger.log.error("Utente non salvato");
    	}
    	return utente;	
    }
    
    public UtenteEntity updateUtente(Long id, UtenteUpdateDto body) {
    	UtenteEntity utente = utenteRepository.findById(id).get();
    	UtenteEntity updatedUtente = null;
    	List<GruppoEntity> gruppi = new ArrayList<>();
    	
    	if (utente != null) {
    		utenteLogger.log.info("Utente: " + utente);
    		if(body.nome() != null) {
    			utente.setNomeUtente(body.nome());
    		}
    		if(body.cognome() != null) {
    			utente.setCognomeUtente(body.cognome());;
    		}
    		if(body.email() != null) {
    			utente.setEmailUtente(body.email());
    		}
    		if(body.informazioniGenerali() != null) {
    			utente.setInformazioniGeneraliUtente(body.informazioniGenerali());
    		}
    		if(body.gruppiId() != null) {
    			for (int i = 0; i < body.gruppiId().size(); i++) {
    				GruppoEntity gruppo = gruppoService.getGruppo(body.gruppiId().get(i));
    				gruppi.add(gruppo);
    			}
    			utente.setGruppi(gruppi);
    		} else {
    			System.out.println("non entra");
    		}
    		if(body.ruolo() != null) {
    			utente.setRuolo(ruoloService.getRuolo(body.ruolo()));
    		}
    		utente.setDataModificaUtente(LocalDateTime.now());
    		utente.setFlgCancellatoUtente(false);
    		updatedUtente = utenteRepository.save(utente);
    		utenteLogger.log.info("Utente aggiornato: " + updatedUtente);
    	} else {
    		utenteLogger.log.error("Utente non aggiornato");
    	}
    	return updatedUtente;
    }

    public void deleteUtente(Long id) {
    	utenteRepository.deleteById(id);
    }
    
    public UtenteEntity findById(long id) throws NotFoundException {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    
    public UtenteEntity findByEmail(String email) throws Exception {
        return utenteRepository.findByEmailUtente(email)
                .orElseThrow(() -> new NotFoundException("Utente con email "+ email + " non trovato"));
    }
    
    public UtenteEntity trashUtente(Long id, UtenteEntity utenteEntity) {
		UtenteEntity utente = utenteRepository.findById(id).get();
		UtenteEntity trashUtente = null;
		
		if (utente != null && !utente.isFlgCancellatoUtente()) {
			utente.setFlgCancellatoUtente(true);
			trashUtente = utenteRepository.save(utente);
			utenteLogger.log.info("Utente spostato nel cestino: " + trashUtente);
		} else {
			utenteLogger.log.info("Utente non spostato nel cestino");
		}
		return trashUtente;
	}

    public Resource generatePdf(UtenteEntity currentUser) throws Exception {
		// Genera il documento PDF per l'utente
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        document.add(new Paragraph("Nome: " + currentUser.getNomeUtente()));
        document.add(new Paragraph("Cognome: " + currentUser.getCognomeUtente()));
        document.add(new Paragraph("Email: " + currentUser.getEmailUtente()));
        document.add(new Paragraph("Informazioni Generali" + currentUser.getInformazioniGeneraliUtente()));
        // Aggiungi altre informazioni sull'utente al documento
        document.close();

        // Converte il ByteArrayOutputStream in ByteArrayResource
        byte[] pdfBytes = outputStream.toByteArray();
        
        List<UtenteEntity> responsabiliList = new ArrayList<>();
        for(GruppoEntity gruppo : currentUser.getGruppi()) {
        	UtenteEntity responsabile = gruppo.getResponsabile();
        	responsabiliList.add(responsabile);
        }
        
        for(UtenteEntity utente : responsabiliList) {
        	emailService.send("amministrazione@gmail.com", utente.getEmailUtente(), "Pdf scaricato", "L'utente " + currentUser.getNomeUtente() + " " + currentUser.getCognomeUtente() + "  ha scaricato il pdf");
           }
        
        
        return new ByteArrayResource(pdfBytes);
		
    }
}
