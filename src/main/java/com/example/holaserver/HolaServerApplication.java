package com.example.holaserver;

import lombok.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Map;

@SpringBootApplication
@EnableJpaAuditing
public class HolaServerApplication {
    public static void main(String[] args) {
        System.out.println("START PUMP SERVER APPLICATION");
        SpringApplication.run(HolaServerApplication.class, args);
    }
}
