package com.example.holaserver.Auth;

import lombok.Getter;

@Getter
public class OauthTokenResponse {
    private String access_token;
    private String refresh_token;
}
