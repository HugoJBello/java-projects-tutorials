package com.batch.steps;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * {@link ItemReader} with hard-coded input data.
 */

@Component("readerStep3")
@Scope("step")
public class NoOpItemReaderStep3 implements ItemReader<String> {
	
	private static final Log log = LogFactory.getLog(NoOpItemReaderStep3.class);
	
	@Value("#{jobExecutionContext['someStringToPass']}")
	private String someStringFromPrevStep;
	
	private int index = 0;
	
	/**
	 * Reads next record from input
	 */
	public String read() throws Exception {
		if (index < 1) {
			log.info("------------------------------------------");
			log.info("Inside step 3");
			log.info("This string is from step 1: "+ someStringFromPrevStep);
			log.info("------------------------------------------");

 			index++;
			return "done";
	 	}
		else {
			return null;
		}
		
	}
//	@BeforeStep
//    public void retrieveInterstepData(StepExecution stepExecution) {
//        JobExecution jobExecution = stepExecution.getJobExecution();
//        ExecutionContext jobContext = jobExecution.getExecutionContext();
//        this.someStringFromPrevStep = (String) jobContext.get("someStringToPass");
//    }

}
