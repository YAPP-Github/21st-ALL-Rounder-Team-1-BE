package com.example.holaserver.User;

import com.example.holaserver.Auth.PrincipalDetails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Getter
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public Long getUserId(){
        Authentication user = SecurityContextHolder.getContext().getAuthentication();

        PrincipalDetails principalDetails = (PrincipalDetails) user.getPrincipal();
        Long userId = principalDetails.getUserId();
        return userId;
    }

    public UserInfoResponse loadMyInfo(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        UserInfoResponse response = UserInfoResponse.builder()
                .entity(user)
                .build();
        return response;
    }

    public Boolean findDuplicated(String nickname){
        return userRepository.existsByNickname(nickname);
    }
}