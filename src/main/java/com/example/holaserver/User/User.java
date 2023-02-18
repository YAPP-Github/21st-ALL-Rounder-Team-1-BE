package com.example.holaserver.User;

import com.example.holaserver.Common.BaseTimeEntity;
import com.example.holaserver.User.Enum.Type;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="user")
@Getter
@Where(clause = "removed_at IS NULL")
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

    private Timestamp removedAt;

    @Builder
    public User(String name, String nickname, String email, Type type, String oauthType, Byte rating, String imgPath, String oauthIdentity) {
        this.name = name;
        this.nickname = nickname;
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
        this.nickname = user.getNickname();
        if(user.getEmail() != null)
            this.email = user.getEmail();
        if(user.getPhoneNumber() != null)
            this.phoneNumber = user.getPhoneNumber();
        this.rating = user.getRating();
        this.imgPath = user.getImgPath();
    }

    public void removeUser(){
        this.removedAt = new Timestamp(System.currentTimeMillis());
    }

    public void modifyUserRating(int rating) {
        this.rating = (byte) rating;
    }
}
