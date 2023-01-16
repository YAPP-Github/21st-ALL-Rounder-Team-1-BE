package com.example.holaserver.User;

import com.example.holaserver.Auth.KaKaoLoginResponse;
import com.example.holaserver.Auth.OauthService;
import com.example.holaserver.Common.response.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final OauthService oauthService;
    @GetMapping("/login/oauth/kakao")
    public ResponseTemplate<KaKaoLoginResponse> kakaoLogin(@RequestParam String code){

        KaKaoLoginResponse kaKaoLoginResponse = oauthService.kakaoLogin(code);

        return new ResponseTemplate<>(kaKaoLoginResponse, "로그인 성공");
    }

    @GetMapping("/user/{nickname}")
    public ResponseTemplate<Boolean> findNickname(@PathVariable String nickname){
        return new ResponseTemplate<>(userService.findDuplicated(nickname), "닉네임 중복 확인 완료(중복일 시 True)");
    }

    @GetMapping("/user/myinfo")
    public ResponseTemplate<UserInfoResponse> loadMyinfo() {
        Long userId = userService.getUserId();
        System.out.println("userId : " + userId);
        UserInfoResponse response = userService.loadMyInfo(userId);
        return new ResponseTemplate<>(response, "마이페이지 정보 로딩 성공");

    }

}
