package com.example.holaserver.Store.DTO;

import com.example.holaserver.Store.Store;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SaveStoreRequestDto {
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

    public Store createSaveStoreBuilder() {
        return Store.builder()
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
}
