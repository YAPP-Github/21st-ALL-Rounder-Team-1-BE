package com.example.holaserver.Auth.userinfo;

import java.util.Map;

public interface OAuth2UserInfo {
    Map<String, Object> getAttributes();
    String getOauthIdentity();
    String getOauthType();
    String getEmail();
    String getName();
}