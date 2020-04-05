package com.topsail.batch.demo.example11.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Steven
 * @date 2020-04-05
 */
@Slf4j
@Configuration
public class DbDemoJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DbJdbcDemoWriter dbJdbcDemoWriter;

    @Bean
    public Job dbJdbcDemoJob() {
        return jobBuilderFactory.get("dbJdbcDemoJob")
                .start(dbJdbcDemoStep())
                .build();
    }

    @Bean
    public Step dbJdbcDemoStep() {
        return stepBuilderFactory.get("dbJdbcDemoStep123123")
                .<Customer, Customer>chunk(100)
                .reader(dbJdbcDemoReader())
                .writer(dbJdbcDemoWriter)
                .build();
    }

    @Bean
    @StepScope
    public JdbcPagingItemReader<Customer> dbJdbcDemoReader() {
        JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        reader.setFetchSize(100);
        reader.setRowMapper(new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Customer customer = Customer.builder()
                        .id(rs.getLong("id"))
                        .firstName(rs.getString("firstName"))
                        .lastName(rs.getString("lastName"))
                        .birthday(rs.getDate("birthday"))
                        .build();
                return customer;
            }
        });

        MySqlPagingQueryProvider mySqlPagingQueryProvider = new MySqlPagingQueryProvider();
        mySqlPagingQueryProvider.setSelectClause("id, firstName, lastName, birthday");
        mySqlPagingQueryProvider.setFromClause("from Customer");

        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("id", Order.ASCENDING);
        mySqlPagingQueryProvider.setSortKeys(sortKeys);

        reader.setQueryProvider(mySqlPagingQueryProvider);

        return reader;
    }

}
