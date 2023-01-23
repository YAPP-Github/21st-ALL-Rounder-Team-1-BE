package com.example.holaserver.User.Dto;

import com.example.holaserver.User.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoResponse {
    private String nickname;
    private String imgPath;
    private Byte rating;

    @Builder
    public UserInfoResponse(User entity){
        this.nickname = entity.getNickname();
        this.imgPath = entity.getImgPath();
        this.rating = entity.getRating();
    }
}
