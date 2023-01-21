package com.example.holaserver.Auth;

import com.example.holaserver.User.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@Getter
@RequiredArgsConstructor
public class OauthService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RestTemplate restTemplate;

    @Value("${CLIENT_ID}")
    String CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    String REDIRECT_URI;

    public KaKaoLoginResponse kakaoLogin(String code) {
        // 1. "인가 코드"로 "액세스 토큰" 요청
        String accessToken = getAccessToken(code);

        // 2. 토큰으로 카카오 API 호출
        SocialUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);
        String userId = Long.toString(kakaoUserInfo.getId());
        User kakaoUser;
        Long savedUserIdx;
        if(findUser(userId, "Kakao")){
            kakaoUser = userRepository.findByOauthIdentity(userId);
            savedUserIdx = kakaoUser.getId();
        } else {
            kakaoUser = User.builder()
                    .name(kakaoUserInfo.getKakao_account().getProfile().getNickname())
                    .email(kakaoUserInfo.getKakao_account().getEmail())
                    .rating((byte) 1)
                    .imgPath(kakaoUserInfo.getKakao_account().getProfile().getProfile_image_url())
                    .oauthIdentity(userId)
                    .oauthType("Kakao")
                    .build();
            savedUserIdx = userRepository.save(kakaoUser).getId();
        }
        String token = jwtTokenProvider.createToken(savedUserIdx);
        KaKaoLoginResponse response = new KaKaoLoginResponse(kakaoUserInfo.getId(), kakaoUser, token);
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
        body.add("redirect_uri", REDIRECT_URI);
        body.add("code", code);

        URI uri = UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com/oauth/token")
                .encode()
                .build()
                .toUri();
        ResponseEntity<OauthTokenResponse> result = restTemplate.postForEntity(uri, body, OauthTokenResponse.class);

        OauthTokenResponse response = result.getBody();

        String token = response.getAccess_token();

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