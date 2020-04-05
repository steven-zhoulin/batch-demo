package com.topsail.batch.demo.example11;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Steven
 * @date 2020-04-05
 */
@EnableBatchProcessing
@SpringBootApplication
public class Example11Applicatioin {

    public static void main(String[] args) {
        SpringApplication.run(Example11Applicatioin.class, args);
    }

}
