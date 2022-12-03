package com.example.holaserver.Store;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String name;
    private String latitude;
    private String longitude;
    private String businessHour;
    private String address;
    private String instaAccount;
    private String callNumber;
    private Integer remommendation;
    private String registrationNumber;
    private Date createdAt;
    private Date modifiedAt;
    private Date removedAt;

    public Store() {}
}
