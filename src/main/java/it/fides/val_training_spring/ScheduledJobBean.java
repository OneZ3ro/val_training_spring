package it.fides.val_training_spring;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import it.fides.val_training_spring.utils.loggers.GeneralLogger;

@Component
public class ScheduledJobBean {
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;
	
	@Autowired
	private GeneralLogger logger;

	@Scheduled(cron = "45 * * * * ?")
	public void perform1() throws Exception {
		JobParameters params = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(job, params);
		logger.log.info("bath partito in questo momento");
	}
	
	@Scheduled(cron = "0 25 11 * * ?")
	public void perform2() throws Exception {
		JobParameters params = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(job, params);
		logger.log.info("bath partito in questo momento");
	}
}
