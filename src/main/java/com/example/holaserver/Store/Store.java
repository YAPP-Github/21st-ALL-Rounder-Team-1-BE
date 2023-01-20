package com.example.holaserver.Store;

import com.example.holaserver.Common.BaseTimeEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@NoArgsConstructor
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
    private String imgPath;
    private String instaAccount;
    private String callNumber;
    private String registrationNumber;
    private Boolean isDayOff;
    private Boolean isReady;
    private Date removedAt;

    @Builder
    public Store(
            Long userId,
            String name,
            String status,
            String latitude,
            String longitude,
            String businessHour,
            String notice,
            String address,
            String imgPath,
            String instaAccount,
            String callNumber,
            String registrationNumber,
            Boolean isDayOff,
            Boolean isReady
    ) {
        this.userId = userId;
        this.name = name;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.businessHour = businessHour;
        this.notice = notice;
        this.address = address;
        this.imgPath = imgPath;
        this.instaAccount = instaAccount;
        this.callNumber = callNumber;
        this.registrationNumber = registrationNumber;
        this.isDayOff = isDayOff;
        this.isReady = isReady;
    }
}
