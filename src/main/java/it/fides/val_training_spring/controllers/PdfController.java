package it.fides.val_training_spring.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import it.fides.val_training_spring.batch.BatchConfiguration;
import it.fides.val_training_spring.models.entities.UtenteEntity;
import it.fides.val_training_spring.services.UtenteService;
import jakarta.annotation.Resource;

@RestController@RequestMapping("/salvaPdf")
public class PdfController {
	
	@Autowired
	BatchConfiguration batch;
	
	@Autowired
    private UtenteService utenteService;
	
	@GetMapping("/{id}")
	public ResponseEntity<InputStreamResource> getPdf(@PathVariable Long id) throws FileNotFoundException, DocumentException {
		UtenteEntity utenteEntity = utenteService.findById(id);
		if(utenteEntity.getRuolo().getIdRuolo() == 2) {
			//genero PDF
			String pdfName = "./pdf/"+utenteEntity.getIdUtente()+utenteEntity.getNomeUtente()+utenteEntity.getCognomeUtente()+".pdf";
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(pdfName));
            document.open();
        	
        	document.add(new Paragraph(utenteEntity.getNomeUtente()));
        	document.add(new Paragraph(utenteEntity.getCognomeUtente()));
        	document.add(new Paragraph(utenteEntity.getEmailUtente()));
        	document.close();
			
			//download del pdf
        	File downloadFile = new File(pdfName);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(downloadFile));
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
        	
        	//se è dipendente manda la mail ai suoi responsabili
			
		} else return null;

	}
}
