package com.topsail.batch.demo.example5.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * @author Steven
 * @date 2020-04-04
 */
@Slf4j
@Configuration
@EnableBatchProcessing
public class JobSplitDemoConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobSplitDemo() {
        return jobBuilderFactory.get("jobSplitDemo")
                .start(flow1())
                .split(new SimpleAsyncTaskExecutor()).add(flow2())
                .end()
                .build();
    }

    @Bean
    public Flow flow1() {
        return new FlowBuilder<Flow>("flow1")
                .start(
                        stepBuilderFactory.get("jobSplitStep1")
                                .tasklet(tasklet())
                                .build()
                ).build();
    }

    @Bean
    public Flow flow2() {
        return new FlowBuilder<Flow>("flow2")
                .start(
                        stepBuilderFactory.get("jobSplitStep2")
                                .tasklet(tasklet())
                                .build()
                )
                .next(
                        stepBuilderFactory.get("jobSplitStep3")
                                .tasklet(tasklet())
                                .build()
                )
                .build();
    }

    private Tasklet tasklet() {
        return new MyTasklet();
    }

}
