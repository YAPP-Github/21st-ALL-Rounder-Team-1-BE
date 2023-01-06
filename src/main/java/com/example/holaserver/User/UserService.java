package com.example.holaserver.User;

import com.example.holaserver.Auth.JwtTokenProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
@Service
@Getter
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private final RestTemplate restTemplate;

    @Value("${CLIENT_ID}")
    String CLIENT_ID;

    public LoginResponse login(String code) {
        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getAccessToken(code);

        // 2. 토큰으로 카카오 API 호출
        SocialUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);
        String userId = Long.toString(kakaoUserInfo.getId());
        User kakaoUser;
        if(findUser(userId, "Kakao")){
            kakaoUser = userRepository.findByOauthIdentity(userId);
        } else {
            kakaoUser = User.oauth2Register()
                    .name(kakaoUserInfo.getName())
                    .email(kakaoUserInfo.getEmail())
                    .type(Type.TYPE_USER)
                    .oauthIdentity(userId)
                    .oauthType("Kakao")
                    .build();
            userRepository.save(kakaoUser);
        }
        String token = jwtTokenProvider.createToken(accessToken, 120000);
        LoginResponse response = new LoginResponse(kakaoUserInfo.getId(), kakaoUser, token);
        return response;
    }

    // 1. "인가 코드"로 "액세스 토큰" 요청
    private String getAccessToken(String code) {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", CLIENT_ID);
        body.add("redirect_uri", "http://localhost:3000/login/oauth/kakao");
        body.add("code", code);

        URI uri = UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com/oauth/token")
                .encode()
                .build()
                .toUri();
        ResponseEntity<OauthTokenResponse> result = restTemplate.postForEntity(uri, body, OauthTokenResponse.class);

        OauthTokenResponse response = result.getBody();
        System.out.println(result.getBody());
        if(result.getBody() == null){
            System.out.println("어디서부터 잘못된걸까..?");
        }

        String token = response.getAccess_token();

        System.out.println("Token!!!!!!! : "+token);
        return token;
    }

    // 2. 토큰으로 카카오 API 호출
    private SocialUserInfoDto getKakaoUserInfo(String accessToken) {
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP 요청 보내기
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<SocialUserInfoDto> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                request,
                SocialUserInfoDto.class);
        return response.getBody();
    }

    Boolean findUser (String oauthIdentity, String oauthType) {
        return userRepository.existsByOauthIdentityAndOauthType(oauthIdentity, oauthType);
    }

}