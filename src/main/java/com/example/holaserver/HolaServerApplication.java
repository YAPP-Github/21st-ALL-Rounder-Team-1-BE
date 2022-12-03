package com.example.holaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HolaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HolaServerApplication.class, args);
    }
}
