package com.example.holaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) // db 무시
public class HolaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HolaServerApplication.class, args);
    }
}
