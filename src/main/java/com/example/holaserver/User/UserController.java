package com.example.holaserver.User;

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
    @GetMapping("/login/oauth/kakao")
    public ResponseTemplate<LoginResponse> login(@RequestParam String code){

        LoginResponse loginResponse = userService.login(code);

        return new ResponseTemplate<>(loginResponse, "로그인 성공");
    }

}
