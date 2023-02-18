package com.example.holaserver.Store.DTO;

import com.example.holaserver.Store.ImgStore.ImgStore;
import com.example.holaserver.Store.StoreRefillGuide.StoreRefillGuide;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class StoreByLongitudeAndLatitudeResponse {
    private Long id;
    private Long userId;
    private String name;
    private String status;
    private String longitude;
    private String latitude;
    private List<Map<String, Object>> businessHour;
    private String notice;
    private String address;
    private String instaAccount;
    private String callNumber;
    private String registrationNumber;
    private Boolean isReady;
    private String distance;
    private List<ImgStore> imgStores;
    private List<StoreRefillGuide> storeRefillGuides;

    public StoreByLongitudeAndLatitudeResponse(StoreByLongitudeAndLatitudeInterface store, List<ImgStore> imgStores, List<StoreRefillGuide> storeRefillGuides) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        this.id = store.getId();
        this.userId = store.getUserId();
        this.name = store.getName();
        this.status = store.getStatus();
        this.longitude = store.getLongitude();
        this.latitude = store.getLatitude();
        this.businessHour = mapper.readValue(store.getBusiness_Hour(), new TypeReference<>() {});
        this.notice = store.getNotice();
        this.address = store.getAddress();
        this.instaAccount = store.getInsta_Account();
        this.callNumber = store.getCall_Number();
        this.registrationNumber = store.getRegistration_Number();
        this.isReady = store.getIsReady();
        this.distance = store.getDistance();
        this.imgStores = imgStores;
        this.storeRefillGuides = storeRefillGuides;
    }
}
