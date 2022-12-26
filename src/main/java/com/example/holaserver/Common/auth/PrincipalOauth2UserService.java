package com.example.holaserver.Common.auth;

import com.example.holaserver.Common.auth.userinfo.KakaoUserInfo;
import com.example.holaserver.Common.auth.userinfo.OAuth2UserInfo;
import com.example.holaserver.User.Type;
import com.example.holaserver.User.User;
import com.example.holaserver.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    //private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;
        String oauthType = userRequest.getClientRegistration().getRegistrationId();

        if(oauthType.equals("naver")){ // naver 추가 후 해제 예정
            //oAuth2UserInfo = new NaverUserInfo(oAuth2User.getAttributes());
        }
        else if(oauthType.equals("kakao")){
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }

        String oauthIdentity = oAuth2UserInfo.getOauthIdentity();
        String name = oauthType+"_"+oauthIdentity;

        //String uuid = UUID.randomUUID().toString().substring(0, 6);
        //String password = bCryptPasswordEncoder.encode("패스워드"+uuid);

        String email = oAuth2UserInfo.getEmail();
        Type type = Type.TYPE_USER;

        User byUsername = userRepository.findByName(name);

        //DB에 없는 사용자라면 회원가입처리
        if(byUsername == null){
            byUsername = User.oauth2Register()
                    .name(name)
                    .email(email)
                    .type(type)
                    .oauthType(oauthType)
                    .oauthIndentity(oauthIdentity)
                    .build();
            userRepository.save(byUsername);
        }

        return new PrincipalDetails(byUsername, oAuth2UserInfo);
    }
}
