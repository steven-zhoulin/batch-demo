package com.topsail.batch.demo.example5.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * @author Steven
 * @date 2020-04-04
 */
@Slf4j
public class MyTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("==> thread: {}, task: {}",
                Thread.currentThread().getName(),
                chunkContext.getStepContext().getStepName());
        return RepeatStatus.FINISHED;
    }
}
