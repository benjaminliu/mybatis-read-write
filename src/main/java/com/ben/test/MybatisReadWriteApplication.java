package com.ben.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//exclude default DataSourceAutoConfiguration to avoid circle reference
@MapperScan("com.ben.test.dao")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MybatisReadWriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisReadWriteApplication.class, args);
    }
}
