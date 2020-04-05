package com.topsail.batch.demo.example10;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Steven
 * @date 2020-04-05
 */
@EnableBatchProcessing
@SpringBootApplication
public class Example10Applicatioin {

    public static void main(String[] args) {
        SpringApplication.run(Example10Applicatioin.class, args);
    }

}
