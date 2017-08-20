package com.batch.steps;

import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component("taskletStep1")
public class TaskletStep1 implements Tasklet{
 
	private static final Log log = LogFactory.getLog(TaskletStep1.class);
    
    private String someStringToPass="";
    Random generator = new Random();
    int randomInt = generator.nextInt();
    @Override
    public RepeatStatus execute(StepContribution contribution,
            ChunkContext chunkContext) throws Exception {
    	log.info("------------------------------------------");
		log.info("Inside step 1");
        
 		someStringToPass = "" + randomInt;
 		
 		//Here is where we storage the parameter in to the context for future steps:
		chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("someStringToPass", someStringToPass);
		
		log.info("Passing " + someStringToPass + " to next steps");
		log.info("------------------------------------------");

        return RepeatStatus.FINISHED;
    }
    
     
}