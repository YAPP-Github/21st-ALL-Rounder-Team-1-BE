package com.example.holaserver.Common;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Common {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String name;

    public Common() {}

    public Common(Long id, Long userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }
}
