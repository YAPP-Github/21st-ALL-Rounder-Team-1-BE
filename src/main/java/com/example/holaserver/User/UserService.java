package com.example.holaserver.User;

import com.example.holaserver.Auth.AuthService;
import com.example.holaserver.Auth.OauthService;
import com.example.holaserver.User.Dto.BossSaveBody;
import com.example.holaserver.User.Dto.ProfileEditBody;
import com.example.holaserver.User.Dto.UserInfoResponse;
import javassist.NotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public User updateBoss(BossSaveBody bossSaveBody) throws NotFoundException {
        Long userId = authService.getPayloadByToken();
        if(userId == null) throw new NotFoundException("올바르지 않은 토큰입니다.");
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        user.saveBoss(bossSaveBody.getName(), bossSaveBody.getEmail(), bossSaveBody.getPhoneNumber());
        return user;
    }

    public UserInfoResponse editProfile(ProfileEditBody profileEditBody) throws NotFoundException {
        Long userId = authService.getPayloadByToken();
        if(userId == null) throw new NotFoundException("올바르지 않은 토큰입니다.");
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        user.editProfile(profileEditBody.getNickname(), profileEditBody.getImgPath());
        return UserInfoResponse.builder().entity(user).build();
    }

    public Optional<User> findUser() {
        return userRepository.findById(authService.getPayloadByToken());
    }
}