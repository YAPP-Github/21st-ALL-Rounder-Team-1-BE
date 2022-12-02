package com.example.holaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;

@SpringBootApplication
public class HolaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HolaServerApplication.class, args);
    }
}
