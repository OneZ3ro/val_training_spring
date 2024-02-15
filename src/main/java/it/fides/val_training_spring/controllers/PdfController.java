package it.fides.val_training_spring.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.services.UtenteService;

@RestController
@RequestMapping("/salvaPdf")
public class PdfController {
	
	@Autowired
	UtenteService utenteService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Resource> salvaPdf(@PathVariable Long id) throws Exception {
		
		UtenteEntity utente = utenteService.findById(id);
		
		//Trovo utente
		String pdfName = utente.getIdUtente()+utente.getNomeUtente()+utente.getCognomeUtente()+".pdf";
		
		//download PDF
	    File downloadFile = new File(pdfName);
	    InputStreamResource resource = new InputStreamResource(new FileInputStream(downloadFile));
	    if(!resource.exists()) { //se non esiste lo crea
			Document document = new Document();
	        
	        PdfWriter.getInstance(document, new FileOutputStream(pdfName));
	        document.open();
	    	
	    	document.add(new Paragraph(utente.getNomeUtente()));
	    	document.close();
	    }
	    HttpHeaders header = new HttpHeaders();
	    header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadFile.getName());
	    header.add("Cache-Control", "no-cache, no-store, must-revalidate");
	    header.add("Pragma", "no-cache");
	    header.add("Expires", "0");
	    return ResponseEntity.ok()
	        .headers(header)
	        .contentLength(downloadFile.length())
	        .contentType(MediaType.parseMediaType("application/octet-stream"))
	        .body(resource);
	}
    
}
