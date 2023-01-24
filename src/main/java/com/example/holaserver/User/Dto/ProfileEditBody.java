package com.example.holaserver.User.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileEditBody {
    private String nickname;
    private String imgPath;
}
