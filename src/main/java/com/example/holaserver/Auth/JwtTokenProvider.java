package com.example.holaserver.Auth;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("1800000")
    private Long accessTokenValidityInMilliseconds;

    @Value("1209600000")
    private Long refreshTokenValidityInMilliseconds;

    @Value("ENC(GxD4/mAC8zzpTpiWJbfhLmi6QAYXOc10pjmOVcMawbcnNnc7ptcKj0C9TAJSZuQXf2ef8C9BCTiFePY9aKy79/T1YwU2vC7gwMQVXJa4CVgzw9cNEtfo2jcHrGMzZli0xZ7vK3ibeTVPon5ZygRkdPwzzrnP+G/qT7fy/sKJ17ie6mBofs8bl/F0sVnuDYct)")
    private String secretKey;

    public String createAccessToken(String payload) {
        return createToken(payload, accessTokenValidityInMilliseconds);
    }

    public String createRefreshToken() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        return createToken(generatedString, refreshTokenValidityInMilliseconds);
    }

    public String createToken(String payload, long expireLength) {
        Claims claims = Jwts.claims().setSubject(payload);
        Date now = new Date();
        Date validity = new Date(now.getTime() + expireLength);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }

    public String getPayload(String token){
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        } catch (JwtException e){
            throw new RuntimeException("유효하지 않은 토큰 입니다");
        }
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException exception) {
            return false;
        }
    }
}

