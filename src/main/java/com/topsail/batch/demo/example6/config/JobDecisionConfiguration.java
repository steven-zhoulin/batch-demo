package com.topsail.batch.demo.example6.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Steven
 * @date 2020-04-04
 */
@Slf4j
@Configuration
@EnableBatchProcessing
public class JobDecisionConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowDecisionJobDemo() {
        return jobBuilderFactory.get("flowDecisionJobDemo")
                .start(firstStep())
                .next(myDecider())
                .from(myDecider()).on("EVEN").to(evenStep())
                .from(myDecider()).on("ODD").to(oddStep())
                .from(oddStep()).on("*").to(myDecider())
                .end()
                .build();
    }

    @Bean
    public JobExecutionDecider myDecider() {
        return new MyDecider();
    }

    @Bean
    public Step firstStep() {
        return stepBuilderFactory.get("firstStep").tasklet((contribution, chunkContext) -> {
            log.info("firstStep...");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step oddStep() {
        return stepBuilderFactory.get("oddStep").tasklet((contribution, chunkContext) -> {
            log.info("oddStep...");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step evenStep() {
        return stepBuilderFactory.get("evenStep").tasklet((contribution, chunkContext) -> {
            log.info("evenStep...");
            return RepeatStatus.FINISHED;
        }).build();
    }


    private class MyDecider implements JobExecutionDecider {

        private long count = 0L;

        @Override
        public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {

            this.count++;
            if (count % 2 == 0) {
                return new FlowExecutionStatus("EVEN");
            } else {
                return new FlowExecutionStatus("ODD");
            }

        }
    }
}
