package com.topsail.batch.demo.example9.listener;

import lombok.Getter;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author Steven
 * @date 2020-04-05
 */
@Component
public class InitListener implements StepExecutionListener {

    @Getter
    private JobParameters jobParameters;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.jobParameters = stepExecution.getJobParameters();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
