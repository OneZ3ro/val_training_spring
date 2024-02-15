package it.fides.val_training_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;

import it.fides.val_training_spring.models.dto.UtenteUpdateDto;
import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.services.AuthService;
import it.fides.val_training_spring.services.UtenteService;
import java.util.List;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;
    
    @Autowired
    private AuthService authService;

    @GetMapping
    public List<UtenteEntity> getAllUtenti() {
        return utenteService.getAllUtenti();
    }
    
    @GetMapping("/me")
    public UserDetails getProfile(@AuthenticationPrincipal UserDetails currentUser){
        return currentUser;
    };
    
    @PutMapping("/me")
    public UtenteEntity updateProfileUser(@AuthenticationPrincipal UtenteEntity currentUser, @RequestBody @Validated UtenteUpdateDto body) {
        return authService.updateUtenteById(currentUser.getIdUtente(), body);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('responsabile')")
    public UtenteEntity getUtente(@PathVariable Long id) {
        return utenteService.getUtente(id);
    }
    
    @PostMapping
    public UtenteEntity insertUtente(@RequestBody UtenteEntity utenteEntity) {
        return utenteService.insertUtente(utenteEntity);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('responsabile')")
    public UtenteEntity updateUtente(@PathVariable Long id, @RequestBody UtenteUpdateDto body) {
    	System.out.println("è entrato controller");
        return utenteService.updateUtente(id, body);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public void deleteUtente(@PathVariable Long id) {
    	utenteService.deleteUtente(id);
    }
    
    @PutMapping("/trash/{id}")
    @PreAuthorize("hasAuthority('admin')")
	public UtenteEntity trashUtente(@PathVariable Long id, @RequestBody UtenteEntity utenteEntity) {
		return utenteService.trashUtente(id, utenteEntity);
	}
    
    @GetMapping("/me/downloadPdf")
    public ResponseEntity<Resource> downloadPdf(@AuthenticationPrincipal UtenteEntity currentUser) throws Exception{
    	Resource pdfResource = utenteService.generatePdf(currentUser);

        // Imposta gli header per indicare che si tratta di un download e specifica il nome del file
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=utente.pdf");

        // Restituisci una risposta con il contenuto del file PDF
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfResource);
    }
}
