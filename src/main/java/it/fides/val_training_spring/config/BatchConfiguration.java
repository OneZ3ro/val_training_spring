package it.fides.val_training_spring.config;

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

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import it.fides.val_training_spring.models.dto.Person;

@Configuration
public class BatchConfiguration {

	@Value("csv/inputData.csv")
	private String fileInput;

	@Bean
	public Job importUserJob(JobRepository jobRepository, Step step1) {
		return new JobBuilder("importUserJob", jobRepository).incrementer(new RunIdIncrementer()).flow(step1).end()
				.build();
	}

	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("step1", jobRepository).<Person, Person>chunk(10, transactionManager).reader(reader())
				// .processor(processor())
				.writer(writer()).allowStartIfComplete(true).build();
	}

	@Bean
	public FlatFileItemReader<Person> reader() {
		return new FlatFileItemReaderBuilder<Person>().name("personItemReader")
				.resource(new ClassPathResource(fileInput)).delimited().delimiter(",")
				.names(new String[] { "nome", "cognome" }).fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
					{
						setTargetType(Person.class);
					}
				}).build();
	}

	@Bean
	public ItemWriter<Person> writer() {
		return new ItemWriter<Person>() {
			@Override
			public void write(Chunk<? extends Person> items) throws Exception {
				// qui vanno aggiunti i service per poi stamparli nel file
				// UtenteService utenteService = new UtenteService();
				// GruppoService gruppoService = new GruppoService();
				for (Person utente : items) {

					Document document = new Document();
					PdfWriter.getInstance(document,
							new FileOutputStream(utente.getNome() + utente.getCognome() + ".pdf"));
					document.open();
					document.add(new Paragraph(utente.getNome()));
					document.close();
				}

			}
		};
	}

	/*
	 * @Bean public PersonItemProcessor processor() { return new
	 * PersonItemProcessor(); }
	 */
}