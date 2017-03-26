package com.hjbello;

import javax.batch.api.Decider;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@Bean
	public Job importJob(JobBuilderFactory jobs) {
		FlowBuilder<Flow> flowBuilder = new FlowBuilder<Flow>("flow1");

		Flow flow =  flowBuilder
				.start(step1())
				.next(decision())
				.on(decision().COMPLETED)
				.to(step2())
				.from(decision())
				.on(decision().FAILED)
				.to(step3())
				.end();

		return jobs.get("importUserLoopJob")
				.incrementer(new RunIdIncrementer())
				.start(flow)
				.end()
				.build();
	}

	// STEPS -----------------------------------------

	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<String, String> chunk(10)
				.reader(readerStep1())
				.writer(writerStep1())
				.build();
	} 


	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
				.<String, String> chunk(10)
				.reader(readerStep2())
				.writer(writerStep2())
				.build();
	}

	@Bean
	public Step step3() {
		return stepBuilderFactory.get("step3")
				.tasklet(tasklet())
				.build();
	}

	// TASKLETS READERS AND WRITERS -------------------

	@Bean
	public NoOpItemReaderStep2 readerStep2(){
		return new NoOpItemReaderStep2();	
	}

	@Bean
	public NoOpItemReaderStep1 readerStep1(){
		return new NoOpItemReaderStep1();	
	}

	@Bean
	public NoOpItemWriterStep2 writerStep2(){
		return new NoOpItemWriterStep2();	
	}

	@Bean
	public NoOpItemWriterStep1 writerStep1(){
		return new NoOpItemWriterStep1();	
	}

	@Bean
	public TaskletStep3 tasklet(){
		return new TaskletStep3();
	}
	@Bean
	public FlowDecision decision(){
		return new FlowDecision();
	}

}