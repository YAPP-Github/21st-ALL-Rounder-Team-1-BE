package com.example.holaserver.User.Dto;

import com.example.holaserver.User.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoResponse {
    private final String nickname;
    private final String imgPath;
    private final Byte rating;

    @Builder
    public UserInfoResponse(User entity){
        this.nickname = entity.getNickname();
        this.imgPath = entity.getImgPath();
        this.rating = entity.getRating();
    }
}
