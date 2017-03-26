package com.hjbello;

import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
    public Job importJob() {
        return jobBuilderFactory.get("importUserJob")
                .start(step1()).next(step2()).next(step3())
                .build();
    }

    // STEPS -----------------------------------------
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
        		.tasklet(tasklet())
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
    public Step step3() {
        return stepBuilderFactory.get("step3")
        		.<String, String> chunk(10)
                .reader(readerStep3())
                .writer(writerStep3())
                .build();
    }
    
    // TASKLETS READERS AND WRITERS -------------------
    @Bean
    public TaskletStep1 tasklet(){
    	return new TaskletStep1();
    }
   
    @Bean
    public NoOpItemReaderStep2 readerStep2(){
    return new NoOpItemReaderStep2();	
    }
    
    @Bean
    public NoOpItemReaderStep3 readerStep3(){
    return new NoOpItemReaderStep3();	
    }
    
    @Bean
    public NoOpItemWriterStep2 writerStep2(){
    return new NoOpItemWriterStep2();	
    }

    @Bean
    public NoOpItemWriterStep3 writerStep3(){
    return new NoOpItemWriterStep3();	
    }
}