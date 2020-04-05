package com.topsail.batch.demo.example11.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @author Steven
 * @date 2020-04-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthday;
}
