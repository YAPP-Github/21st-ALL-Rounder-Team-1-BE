package com.example.holaserver.User;

import com.example.holaserver.Auth.PrincipalDetails;
import com.example.holaserver.User.Dto.BossSaveDto;
import com.example.holaserver.User.Dto.UserInfoResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Getter
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public Long getUserId(){
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principalDetails instanceof PrincipalDetails){
            return principalDetails.getUserId();
        } else {
            return null;
        }
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

    public User updateBoss(Long userId, BossSaveDto bossSaveDto){
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        user.saveBoss(bossSaveDto.getName(), bossSaveDto.getEmail(), bossSaveDto.getPhoneNumber());
        return user;
    }
}