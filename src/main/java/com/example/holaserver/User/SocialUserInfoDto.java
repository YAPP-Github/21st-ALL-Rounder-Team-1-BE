package com.example.holaserver.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SocialUserInfoDto {
    private Long id;
    private String name;
    private String email;

    public SocialUserInfoDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
