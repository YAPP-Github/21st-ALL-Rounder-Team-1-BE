package com.example.holaserver.Store.DTO;

public interface StoreByLatitudeAndLongitudeResponse {
    Long getId();
    Long getUserId();
    String getName();
    String getStatus();
    String getLatitude();
    String getLongitude();
    String getBusinessHour();
    String getNotice();
    String getAddress();
    String getInstaAccount();
    String getCallNumber();
    String getRegistrationNumber();
    Boolean getIsReady();
    String getDistance();
}
