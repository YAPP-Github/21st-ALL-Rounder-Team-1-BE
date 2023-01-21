package com.example.holaserver.Auth;

import com.example.holaserver.User.Type;
import com.example.holaserver.User.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoLoginResponse {
    private Long id;
    private String name;
    private String email;
    private String imgPath;
    private Type type;
    private String tokenType;
    private String accessToken;
    private String refreshToken;

    @Builder
    public KakaoLoginResponse(Long id, String name, String email, String imgPath, Type type, String tokenType, String accessToken, String refreshToken) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.imgPath = imgPath;
        this.type = type;
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @Builder
    public KakaoLoginResponse(Long id, User user, String token){
        this.id = id;
        this.name = user.getName();
        this.email = user.getEmail();
        this.imgPath = user.getImgPath();
        this.tokenType = user.getOauthType();
        this.accessToken = token;
    }
}
