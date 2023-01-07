package com.example.holaserver.User;

import com.example.holaserver.Auth.KaKaoLoginResponse;
import com.example.holaserver.Auth.OauthService;
import com.example.holaserver.Common.response.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final OauthService oauthService;
    @GetMapping("/login/oauth/kakao")
    public ResponseTemplate<KaKaoLoginResponse> login(@RequestParam String code){

        KaKaoLoginResponse kaKaoLoginResponse = oauthService.kakaoLogin(code);

        return new ResponseTemplate<>(kaKaoLoginResponse, "로그인 성공");
    }

}
