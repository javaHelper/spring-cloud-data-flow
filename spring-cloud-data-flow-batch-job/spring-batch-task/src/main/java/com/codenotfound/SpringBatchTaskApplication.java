package com.codenotfound;

import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;


@EnableTask
@SpringBootApplication
public class SpringBatchTaskApplication implements CommandLineRunner{
	@Autowired
	JobLauncher jobLauncher;

	@Qualifier(value="capitalizeNamesJob")
	@Autowired
	Job job;

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchTaskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			JobParameters jobParameters = 
					new JobParametersBuilder()
					.addString("jobId", UUID.randomUUID().toString())
					.addLong("time",System.currentTimeMillis()).toJobParameters();

			JobExecution execution = jobLauncher.run(job, jobParameters);
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
