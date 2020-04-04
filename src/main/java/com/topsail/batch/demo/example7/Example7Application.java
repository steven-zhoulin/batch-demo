package com.topsail.batch.demo.example7;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Steven
 * @date 2020-04-04
 */
@EnableBatchProcessing
@SpringBootApplication
public class Example7Application {
    public static void main(String[] args) {
        SpringApplication.run(Example7Application.class, args);
    }
}
