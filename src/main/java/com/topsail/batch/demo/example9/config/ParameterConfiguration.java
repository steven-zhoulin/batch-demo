package com.topsail.batch.demo.example9.config;

import com.topsail.batch.demo.example9.listener.InitListener;
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
 * @date 2020-04-05
 */
@Slf4j
@Configuration
public class ParameterConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private InitListener initListener;

    @Bean
    public Job initParameterDemoJob() {
        return jobBuilderFactory.get("initParameterDemoJob")
                .start(myParameterDemoStep())
                .build();
    }



    @Bean
    public Step myParameterDemoStep() {
        return stepBuilderFactory.get("myParameterDemoStep")
                .listener(initListener)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Parameter is : " + initListener.getJobParameters().getParameters());
                    return RepeatStatus.FINISHED;
                }).build();
    }

}
