package com.batch.decisor;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;

public interface JobExecutionDecider {

    FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution);
 
}