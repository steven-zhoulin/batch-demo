package com.topsail.batch.demo;

import com.topsail.batch.demo.example1.Example1Application;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Example1Application.class)
class Example1ApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement("insert into Customer(firstName, lastName, birthday) values(?,?,?)");

        for (int i = 0; i < 999; i++) {
            stmt.setString(1, RandomStringUtils.randomAlphabetic(8));
            stmt.setString(2, RandomStringUtils.randomAlphabetic(8));
            stmt.setDate(3, new Date(System.currentTimeMillis() - ThreadLocalRandom.current().nextLong(100000000000L)));
            stmt.execute();
        }

        connection.commit();
    }

}
