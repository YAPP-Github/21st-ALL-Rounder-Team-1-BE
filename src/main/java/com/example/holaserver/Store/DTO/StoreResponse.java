package com.example.holaserver.Store.DTO;

import com.example.holaserver.Store.Store;
import lombok.Getter;

@Getter
public class StoreResponse {
    private final Long id;
    private final Long userId;
    private final String name;
    private final String status;
    private final String latitude;
    private final String longitude;
    private final String businessHour;
    private final String notice;
    private final String address;
    private final String instaAccount;
    private final String callNumber;
    private final String registrationNumber;
    private final Boolean isDayOff;
    private final Boolean isReady;

    public StoreResponse(Store entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.name = entity.getName();
        this.status = entity.getStatus();
        this.latitude = entity.getLatitude();
        this.longitude = entity.getLongitude();
        this.businessHour = entity.getBusinessHour();
        this.notice = entity.getNotice();
        this.address = entity.getAddress();
        this.instaAccount = entity.getInstaAccount();
        this.callNumber = entity.getCallNumber();
        this.registrationNumber = entity.getRegistrationNumber();
        this.isDayOff = entity.getIsDayOff();
        this.isReady = entity.getIsReady();
    }
}
