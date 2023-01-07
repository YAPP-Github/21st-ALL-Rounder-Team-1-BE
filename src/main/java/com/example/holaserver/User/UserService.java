package com.example.holaserver.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Getter
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

}