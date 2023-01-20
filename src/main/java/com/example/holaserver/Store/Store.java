package com.example.holaserver.Store;

import com.example.holaserver.Common.BaseTimeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String name;
    private String status;
    private String latitude;
    private String longitude;
    private String businessHour;
    private String notice;
    private String address;
    private String instaAccount;
    private String callNumber;
    private String registrationNumber;
    private Boolean isDayOff;
    private Boolean isReady;
}
