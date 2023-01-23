package com.example.holaserver.Store.DTO;

import com.example.holaserver.Store.Store;
import lombok.*;

import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreSaveBody {
    private Long id;
    private String name;
    private String latitude;
    private String longitude;
    private String businessHour;
    private String notice;
    private String address;
    private String imgPath;
    private String instaAccount;
    private String callNumber;
    private String registrationNumber;

    public Store createSaveStoreBuilder(Long userId) {
        return Store.builder()
                .userId(userId)
                .name(name)
                .status("VIEW")
                .latitude(latitude)
                .longitude(longitude)
                .businessHour(businessHour)
                .notice(notice)
                .address(address)
                .instaAccount(instaAccount)
                .callNumber(callNumber)
                .registrationNumber(registrationNumber)
                .build();
    }

    public Store updateStoreStatusBuilder(Long id, Store storeData) {
        return Store.builder()
                .id(id)
                .userId(storeData.getUserId())
                .name(storeData.getName())
                .status("READY")
                .latitude(storeData.getLatitude())
                .longitude(storeData.getLongitude())
                .businessHour(storeData.getBusinessHour())
                .notice(storeData.getNotice())
                .address(storeData.getAddress())
                .instaAccount(storeData.getInstaAccount())
                .callNumber(storeData.getCallNumber())
                .registrationNumber(storeData.getRegistrationNumber())
                .isDayOff(storeData.getIsDayOff())
                .isReady(storeData.getIsReady())
                .build();
    }
}
