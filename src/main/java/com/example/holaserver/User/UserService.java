package com.example.holaserver.User;

import com.example.holaserver.Auth.AuthService;
import com.example.holaserver.Auth.JwtTokenProvider;
import com.example.holaserver.Auth.OauthService;
import com.example.holaserver.User.Dto.BossSaveBody;
import com.example.holaserver.User.Dto.UserInfoResponse;
import com.example.holaserver.User.Dto.UserSaveBody;
import javassist.NotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Getter
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    private final OauthService oauthService;
    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    public String saveKakaoUser(UserSaveBody userSaveBody) {
        User user = User.builder()
                .name(userSaveBody.getName())
                .email(userSaveBody.getEmail())
                .rating((byte) 1)
                .imgPath(userSaveBody.getImgPath())
                .oauthIdentity(userSaveBody.getOauthIdentity())
                .oauthType("Kakao")
                .build();
        return jwtTokenProvider.createToken(userRepository.save(user).getId());
    }

    public UserInfoResponse loadMyInfo() throws NotFoundException{
        Long userId = authService.getPayloadByToken();
        if(userId == null) throw new NotFoundException("올바르지 않은 토큰입니다.");
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        return UserInfoResponse.builder()
                .entity(user)
                .build();
    }

    public Boolean findDuplicated(String nickname){
        return userRepository.existsByNickname(nickname);
    }

    public User updateBoss(BossSaveBody bossSaveBody) {
        Long userId = authService.getPayloadByToken();
        if(userId == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "올바르지 않은 토큰입니다.");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "해당 유저의 가게가 없습니다."));
        user.saveBoss(bossSaveBody.getName(), bossSaveBody.getEmail(), bossSaveBody.getPhoneNumber());
        return user;
    }

    public User modifyUser(User userModifyBody) throws NotFoundException {
        Long userId = authService.getPayloadByToken();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "존재하지 않는 회원 정보입니다 \uD83E\uDDD0"));
        user.modifyUser(userModifyBody);
        return user;
    }

    public Optional<User> findUser() {
        return userRepository.findById(authService.getPayloadByToken());
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
    }
}