package com.example.holaserver.User;

import com.example.holaserver.Auth.Dto.KakaoLoginResponse;
import com.example.holaserver.Auth.OauthService;
import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.User.Dto.BossSaveBody;
import com.example.holaserver.User.Dto.UserInfoResponse;
import com.example.holaserver.User.Dto.UserSaveBody;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final OauthService oauthService;
    @GetMapping("/login/oauth/kakao")
    public ResponseTemplate<KakaoLoginResponse> kakaoLogin(@RequestParam String code){
        KakaoLoginResponse kaKaoLoginResponse = oauthService.kakaoLogin(code);
        return new ResponseTemplate<>(kaKaoLoginResponse, "로그인 성공");
    }

    @PostMapping("/user/kakao-login")
    public ResponseTemplate<String> saveKakaoUser(@RequestBody UserSaveBody userSaveBody){
        String token = userService.saveKakaoUser(userSaveBody);
        return new ResponseTemplate<>(token, "회원가입 완료");
    }

    @GetMapping("/user/{nickname}")
    public ResponseTemplate<Boolean> findNickname(@PathVariable String nickname){
        return new ResponseTemplate<>(userService.findDuplicated(nickname), "닉네임 중복 확인 완료(중복일 시 True)");
    }

    @GetMapping("/user/myinfo")
    public ResponseTemplate<UserInfoResponse> loadMyinfo() throws NotFoundException {
        UserInfoResponse response = userService.loadMyInfo();
        return new ResponseTemplate<>(response, "마이페이지 정보 로딩 성공");
    }

    @PatchMapping("/user/manager")
    public ResponseTemplate<User> updateBossInfo(@RequestBody BossSaveBody bossSaveBody) throws NotFoundException {
        User response = userService.updateBoss(bossSaveBody);
        return new ResponseTemplate<>(response, "사장님 정보 입력 성공");
    }


    @PatchMapping("/user")
    public ResponseTemplate<User> userModify(@RequestBody User userModifyBody) throws NotFoundException {
        User response = userService.ModifyUser(userModifyBody);
        return new ResponseTemplate<>(response, "유저 수정 성공");
    }

    @GetMapping("/user")
    public ResponseTemplate<Optional<User>> userDetails() {
        return new ResponseTemplate<>(userService.findUser(), "유저 로딩 성공");
    }

    @DeleteMapping("/user")
    public ResponseTemplate<Long> userRemove() throws NotFoundException{
        Long response = userService.removeUser();
        return new ResponseTemplate<>(response, "유저 탈퇴 성공");
    }
}
