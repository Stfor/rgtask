package com.example.rgtask;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.rgtask.mapper")
public class RgtaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(RgtaskApplication.class, args);
    }

}
