package com.topsail.batch.demo.example8.config;

import com.topsail.batch.demo.example8.listener.MyChunkListener;
import com.topsail.batch.demo.example8.listener.MyJobListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author Steven
 * @date 2020-04-05
 */
@Slf4j
@Configuration
public class MyListenerJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job myListenerJob() {

        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$");
        return jobBuilderFactory.get("myListenerJob")
                .start(myListenerStep())
                .listener(new MyJobListener())
                .build();
    }

    @Bean
    public Step myListenerStep() {
        return stepBuilderFactory.get("myListenerStep")
                .<String, String>chunk(2)
                .faultTolerant()
                .listener(new MyChunkListener())
                .reader(reader())
                .writer(writer())
                .build();
    }

    @Bean
    public ItemWriter<String> writer() {
        return items -> {
            for (String item : items) {
                log.info("==> writing item: {}", item);
            }
        };
    }

    @Bean
    public ItemReader<String> reader() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@");
        return new ListItemReader<>(Arrays.asList("first", "second", "third"));
    }

}
