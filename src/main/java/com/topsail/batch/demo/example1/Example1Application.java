package com.topsail.batch.demo.example1;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class Example1Application {

    public static void main(String[] args) {
        SpringApplication.run(Example1Application.class, args);
    }

}
