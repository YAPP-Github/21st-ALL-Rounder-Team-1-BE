package com.example.holaserver.User;

import com.example.holaserver.Auth.Dto.KakaoLoginResponse;
import com.example.holaserver.Auth.OauthService;
import com.example.holaserver.Common.response.ResponseTemplate;
import com.example.holaserver.User.Dto.BossSaveDto;
import com.example.holaserver.User.Dto.ProfileEditBody;
import com.example.holaserver.User.Dto.UserInfoResponse;
import com.example.holaserver.User.Dto.UserSaveBody;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;



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
    public ResponseTemplate<User> updateBossInfo(@RequestBody BossSaveDto bossSaveDto) throws NotFoundException {
        User response = userService.updateBoss(bossSaveDto);
        return new ResponseTemplate<>(response, "사장님 정보 입력 성공");
    }


    @PatchMapping("/user/edit-profile")
    public ResponseTemplate<UserInfoResponse> editProfile(@RequestBody ProfileEditBody profileEditBody) throws NotFoundException {
        UserInfoResponse response = userService.editProfile(profileEditBody);
        return new ResponseTemplate<>(response, "프로필 수정 성공");
    }
}
