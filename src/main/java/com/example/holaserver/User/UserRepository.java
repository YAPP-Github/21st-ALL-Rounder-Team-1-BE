package com.example.holaserver.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByOauthIdentityAndOauthType(String oauthIdentity, String oauthType);
    User findByOauthIdentity(String oauthIdentity);
    Boolean existsByNickname(String nickname);
    int countUserByNicknameContaining(String nickname);
}
