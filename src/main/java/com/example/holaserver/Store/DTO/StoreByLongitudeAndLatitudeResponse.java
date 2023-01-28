package com.example.holaserver.Store.DTO;

import com.example.holaserver.Store.ImgStore.ImgStore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class StoreByLongitudeAndLatitudeResponse {
    private Long id;
    private Long userId;
    private String name;
    private String status;
    private String longitude;
    private String latitude;
    private String businessHour;
    private String notice;
    private String address;
    private String instaAccount;
    private String callNumber;
    private String registrationNumber;
    private Boolean isReady;
    private String distance;
    private List<ImgStore> imgStores;

    public StoreByLongitudeAndLatitudeResponse(StoreByLongitudeAndLatitudeInterface store, List<ImgStore> imgStores) {
        this.id = store.getId();
        this.userId = store.getUserId();
        this.name = store.getName();
        this.status = store.getStatus();
        this.longitude = store.getLongitude();
        this.latitude = store.getLatitude();
        this.businessHour = store.getBusinessHour();
        this.notice = store.getNotice();
        this.address = store.getAddress();
        this.instaAccount = store.getInstaAccount();
        this.callNumber = store.getCallNumber();
        this.registrationNumber = store.getRegistrationNumber();
        this.isReady = store.getIsReady();
        this.distance = store.getDistance();
        this.imgStores = imgStores;

    }
}
