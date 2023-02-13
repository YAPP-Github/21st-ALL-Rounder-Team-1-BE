package com.example.holaserver.Store.DTO;

import com.mysql.cj.xdevapi.JsonArray;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;

public interface StoreByLongitudeAndLatitudeInterface {
    Long getId();
    Long getUserId();
    String getName();
    String getStatus();
    String getLongitude();
    String getLatitude();
    String getBusiness_Hour();
    String getNotice();
    String getAddress();
    String getInsta_Account();
    String getCall_Number();
    String getRegistration_Number();
    Boolean getIsReady();
    String getDistance();
}
