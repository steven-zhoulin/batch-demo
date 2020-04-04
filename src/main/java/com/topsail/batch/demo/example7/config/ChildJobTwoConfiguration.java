package com.topsail.batch.demo.example7.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
public class ChildJobTwoConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step childJob2Step1() {
        return stepBuilderFactory.get("childJob1Step1")
                .tasklet((contribution, chunkContext) -> {
                    log.info("childJob1Step1...");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step childJob2Step2() {
        return stepBuilderFactory.get("childJob1Step2")
                .tasklet((contribution, chunkContext) -> {
                    log.info("childJob1Step2...");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Job childJob2() {
        return jobBuilderFactory.get("childJob2")
                .start(childJob2Step1())
                .next(childJob2Step2())
                .build();
    }


}
