package com.ie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ie.repository")
public class Day3Application {

    public static void main(String[] args) {
        SpringApplication.run(Day3Application.class, args);
    }

}
