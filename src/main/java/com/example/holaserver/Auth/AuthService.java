package com.example.holaserver.Auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
public class AuthService {
    public Long getUserId() {
        PrincipalDetails principalDetails = (PrincipalDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principalDetails instanceof PrincipalDetails){
            return principalDetails.getUserId();
        } else {
            return null;
        }
    }
}
