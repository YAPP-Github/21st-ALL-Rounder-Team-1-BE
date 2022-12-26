package com.example.holaserver.Common.auth;

import com.example.holaserver.Common.auth.userinfo.OAuth2UserInfo;
import com.example.holaserver.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
@ToString
@NoArgsConstructor
public class PrincipalDetails implements UserDetails, OAuth2User {
    private User user;
    private OAuth2UserInfo oAuth2UserInfo;

    public PrincipalDetails(User user, OAuth2UserInfo oAuth2UserInfo) {
        this.user = user;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    /**
     * UserDetails 구현
     * 해당 유저의 권한목록 리턴
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getType().toString();
            }
        });
        return collect;

//        Collection<GrantedAuthority> collectors = new ArrayList<>();
//        collectors.add(() -> "ROLE_"+user.getRole());
//        return collectors;
    }

    /**
     * UserDetails 구현
     * PK값을 반환해준다
     */
    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public String getPassword(){
        return "pwd";
    }

    public String getUserEmail(){
        return user.getEmail();
    }

    /**
     * UserDetails 구현
     * 계정 만료 여부
     *  true : 만료안됨
     *  false : 만료됨
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * UserDetails 구현
     * 계정 잠김 여부
     *  true : 잠기지 않음
     *  false : 잠김
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * UserDetails 구현
     * 계정 비밀번호 만료 여부
     *  true : 만료 안됨
     *  false : 만료됨
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * UserDetails 구현
     * 계정 활성화 여부
     *  true : 활성화됨
     *  false : 활성화 안됨
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


    /**
     * OAuth2User 구현
     * @return
     */
    @Override
    public Map<String, Object> getAttributes() {
        //return attributes;
        return oAuth2UserInfo.getAttributes();
    }

    /**
     * OAuth2User 구현
     * @return
     */
    @Override
    public String getName() {
        //String sub = attributes.get("sub").toString();
        //return sub;
        return oAuth2UserInfo.getOauthIdentity();
    }
}
