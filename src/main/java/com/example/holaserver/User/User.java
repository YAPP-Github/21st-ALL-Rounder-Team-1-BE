package com.example.holaserver.User;

import com.example.holaserver.Common.BaseTimeEntity;
import com.example.holaserver.User.Enum.Type;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="User")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nickname;
    private String email;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Setter
    private Type type;

    private String oauthType;

    private String oauthIdentity;

    private Byte rating;

    private String imgPath;

    @Setter
    private Timestamp removedAt;

    @Builder
    public User(String name, String email, Type type, String oauthType, Byte rating, String imgPath, String oauthIdentity) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.rating = rating;
        this.imgPath = imgPath;
        this.oauthType = oauthType;
        this.oauthIdentity = oauthIdentity;
    }

    public void saveBoss(String name, String email, String phoneNumber){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.type = Type.BOSS;
    }

    public void modifyUser(User user){
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.email = user.email;
        this.phoneNumber = user.getPhoneNumber();
        this.type = user.getType();
        this.oauthType = user.getOauthType();
        this.oauthIdentity = user.getOauthIdentity();
        this.rating = user.getRating();
        this.imgPath = user.getImgPath();
    }
}
