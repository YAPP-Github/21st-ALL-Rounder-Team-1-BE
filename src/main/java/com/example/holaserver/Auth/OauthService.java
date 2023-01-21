package com.example.holaserver.Auth;

import com.example.holaserver.Auth.Dto.KakaoLoginResponse;
import com.example.holaserver.Auth.Dto.OauthTokenResponse;
import com.example.holaserver.Auth.Dto.SocialUserInfoDto;
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

    public KakaoLoginResponse kakaoLogin(String code) {
        String accessToken = getAccessToken(code);

        SocialUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);
        String oauthIdentity = Long.toString(kakaoUserInfo.getId());
        User kakaoUser;
        Long savedUserIdx;
        if(findUser(oauthIdentity, "Kakao")){
            kakaoUser = userRepository.findByOauthIdentity(oauthIdentity);
            savedUserIdx = kakaoUser.getId();
        } else {
            kakaoUser = User.builder()
                    .name(kakaoUserInfo.getKakao_account().getProfile().getNickname())
                    .email(kakaoUserInfo.getKakao_account().getEmail())
                    .rating((byte) 1)
                    .imgPath(kakaoUserInfo.getKakao_account().getProfile().getProfile_image_url())
                    .oauthIdentity(oauthIdentity)
                    .oauthType("Kakao")
                    .build();
            savedUserIdx = userRepository.save(kakaoUser).getId();
        }
        String token = jwtTokenProvider.createToken(savedUserIdx);
        KakaoLoginResponse response = new KakaoLoginResponse(kakaoUserInfo.getId(), kakaoUser, token);
        return response;
    }

    private String getAccessToken(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

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

    private SocialUserInfoDto getKakaoUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

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
