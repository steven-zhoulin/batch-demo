package com.topsail.batch.demo.example8.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @author Steven
 * @date 2020-04-05
 */
@Slf4j
public class MyJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("==> beforeJob");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("==> afterJob");
    }
}
