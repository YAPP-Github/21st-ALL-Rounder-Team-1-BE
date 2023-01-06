package com.example.holaserver.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponse {
    private Long id;
    private String name;
    private String email;
    private Type type;
    private String tokenType;
    private String accessToken;
    private String refreshToken;

    @Builder
    public LoginResponse(Long id, String name, String email, Type type, String tokenType, String accessToken, String refreshToken) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @Builder
    public LoginResponse(Long id, User user, String token){
        this.id = id;
        this.name = user.getName();
        this.email = user.getEmail();
        this.type = user.getType();
        this.tokenType = user.getOauthType();
        this.accessToken = token;
    }
}
