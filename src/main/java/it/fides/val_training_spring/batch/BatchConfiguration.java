package it.fides.val_training_spring.batch;

import java.io.FileOutputStream;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import it.fides.val_training_spring.models.entities.UtenteEntity;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Configuration
public class BatchConfiguration {
    
    @Value("csv/inputData.csv")
    private String fileInput;
    
    @Bean
    public Job importUserJob(JobRepository jobRepository, Step step1) { //JobCompletionNotificationListener listener, Step step1) {
        return new JobBuilder("importUserJob", jobRepository)
          .incrementer(new RunIdIncrementer())
          //.listener(listener)
          .flow(step1)
          .end()
          .build();
    }
    
    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
          .<UtenteEntity, UtenteEntity> chunk(10, transactionManager)
          .allowStartIfComplete(true)
          .reader(reader())
          .processor(processor())
          .writer(writer())
          .build();
    }

    @Bean
    public UtenteItemProcessor processor() {
        return new UtenteItemProcessor();
    }
    
	@Bean
    public FlatFileItemReader<UtenteEntity> reader() {
        return new FlatFileItemReaderBuilder<UtenteEntity>().name("UtenteItemReader")
          .resource(new ClassPathResource(fileInput))
          .delimited()
          .delimiter(",")
          .names(new String[] { "nomeUtente", "cognomeUtente", "emailUtente" })
          .fieldSetMapper(new BeanWrapperFieldSetMapper<UtenteEntity>() {{
              setTargetType(UtenteEntity.class);
          }})
          .build();
	}
	
	@Bean
    public ItemWriter<UtenteEntity> writer() {
		
		return new ItemWriter<UtenteEntity>() {
            @Override
            public void write(Chunk<? extends UtenteEntity> items) throws Exception {
                
                //qui vanno aggiunti i service per poi stamparli nel file
                
                //GruppoService gruppoService = new GruppoService();
                
                for (UtenteEntity utente : items) {
                    
                    Document document = new Document();
                    
                    //UtenteEntity utenteEntity = utenteService.findByEmail(utente.getEmailUtente());
                    
                    PdfWriter.getInstance(document, new FileOutputStream("./pdf/"+utente.getIdUtente()+utente.getNomeUtente()+utente.getCognomeUtente()+".pdf"));
                    document.open();
                	
                	document.add(new Paragraph(utente.getNomeUtente()));
                	document.add(new Paragraph(utente.getCognomeUtente()));
                	document.add(new Paragraph(utente.getEmailUtente()));
                	document.close();
                }
            }
        };
    }
}