package com.topsail.batch.demo.example4.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
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
public class JobFlowDemo2Configuration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobFlowDemoJob() {
        return jobBuilderFactory.get("jobFlowDemoJob")
                .start(jobFlowDemo())
                .next(jobFlowStep3())
                .end()
                .build();
    }

    @Bean
    public Flow jobFlowDemo() {

        return new FlowBuilder<Flow>("jobFlowDemo1")
                .start(jobFlowStep1())
                .next(jobFlowStep2())
                .build();
    }

    @Bean
    public Step jobFlowStep1() {
        return stepBuilderFactory.get("jobFlowStep1")
                .tasklet((contribution, chunkContext) -> {
                    log.info("execution jobFlowStep1...");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step jobFlowStep2() {
        return stepBuilderFactory.get("jobFlowStep2")
                .tasklet((contribution, chunkContext) -> {
                    log.info("execution jobFlowStep2...");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step jobFlowStep3() {
        return stepBuilderFactory.get("jobFlowStep3")
                .tasklet((contribution, chunkContext) -> {
                    log.info("execution jobFlowStep3...");
                    return RepeatStatus.FINISHED;
                }).build();
    }

}
