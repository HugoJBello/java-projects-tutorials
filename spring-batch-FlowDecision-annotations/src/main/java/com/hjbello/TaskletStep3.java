package com.hjbello;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component("taskletStep3")
public class TaskletStep3 implements Tasklet{
 
	private static final Log log = LogFactory.getLog(TaskletStep3.class);
     
    @Override
    public RepeatStatus execute(StepContribution contribution,
            ChunkContext chunkContext) throws Exception {
    	log.info("------------------------------------------");
	log.info("Inside step 3");
	log.info("------------------------------------------");
        
        return RepeatStatus.FINISHED;
    }
     
}
