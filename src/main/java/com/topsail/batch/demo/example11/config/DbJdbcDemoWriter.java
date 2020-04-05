package com.topsail.batch.demo.example11.config;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Steven
 * @date 2020-04-05
 */
@Component("dbJdbcDemoWriter")
public class DbJdbcDemoWriter implements ItemWriter<Customer> {


    @Override
    public void write(List<? extends Customer> items) throws Exception {
        for (Customer item : items) {
            System.out.println("customer: " + item);
        }
    }
}
