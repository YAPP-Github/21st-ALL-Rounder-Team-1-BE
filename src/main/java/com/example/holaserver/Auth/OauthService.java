package com.example.holaserver.Auth;

import com.example.holaserver.Auth.Dto.ApplePublicKeyResponse;
import com.example.holaserver.Auth.Dto.SocialLoginResponse;
import com.example.holaserver.Auth.Dto.OauthTokenResponse;
import com.example.holaserver.Auth.Dto.KakaoUserInfoDto;
import com.example.holaserver.User.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URI;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;


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


    public KakaoLoginResponse kakaoLogin(String accessToken) {
        SocialUserInfoDto kakaoUserInfo = getKakaoUserInfo(accessToken);
        String oauthIdentity = Long.toString(kakaoUserInfo.getId());
        User kakaoUser;
        String token = null;
        if(findUser(oauthIdentity, "Kakao")){
            kakaoUser = userRepository.findByOauthIdentity(oauthIdentity);
            token = jwtTokenProvider.createToken(kakaoUser.getId());
        }
        SocialLoginResponse response = new SocialLoginResponse(kakaoUserInfo, oauthIdentity, token);
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

    private KakaoUserInfoDto getKakaoUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<KakaoUserInfoDto> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                request,
                KakaoUserInfoDto.class);
        return response.getBody();
    }

    Boolean findUser (String oauthIdentity, String oauthType) {
        return userRepository.existsByOauthIdentityAndOauthType(oauthIdentity, oauthType);
    }

    public SocialLoginResponse appleLogin(String identityToken) throws UnsupportedEncodingException, InvalidKeySpecException, NoSuchAlgorithmException, JsonProcessingException {
        Claims userInfo = getAppleUserInfo(identityToken);

        String oauthIdentity = userInfo.getSubject();
        User appleUser;
        String token = null;

        if(findUser(oauthIdentity, "Apple")){
            appleUser = userRepository.findByOauthIdentity(oauthIdentity);
            token = jwtTokenProvider.createToken(appleUser.getId());
        }

        SocialLoginResponse response = new SocialLoginResponse(oauthIdentity, token);
        return response;
    }
    public ApplePublicKeyResponse getAppleAuthPublicKey(){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<ApplePublicKeyResponse> response = restTemplate.exchange(
                "https://appleid.apple.com/auth/keys",
                HttpMethod.GET,
                request,
                ApplePublicKeyResponse.class);
        return response.getBody();
    }

    public Claims getAppleUserInfo(String identityToken) throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException, JsonProcessingException {
        ApplePublicKeyResponse response = getAppleAuthPublicKey();
        String headerOfIdentityToken = identityToken.substring(0, identityToken.indexOf("."));
        Map<String, String> header = new ObjectMapper().readValue(new String(Base64.getDecoder().decode(headerOfIdentityToken), "UTF-8"), Map.class);
        ApplePublicKeyResponse.Key key = response.getMatchedKeyBy(header.get("kid"), header.get("alg"))
                .orElseThrow(() -> new NullPointerException("Failed get public key from apple's id server."));

        byte[] nBytes = Base64.getUrlDecoder().decode(key.getN());
        byte[] eBytes = Base64.getUrlDecoder().decode(key.getE());

        BigInteger n = new BigInteger(1, nBytes);
        BigInteger e = new BigInteger(1, eBytes);

        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);
        KeyFactory keyFactory = KeyFactory.getInstance(key.getKty());
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(identityToken).getBody();
    }

}
