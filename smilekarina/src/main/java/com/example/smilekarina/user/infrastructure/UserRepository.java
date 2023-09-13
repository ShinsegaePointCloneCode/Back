package com.example.smilekarina.user.infrastructure;

import com.example.smilekarina.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByUUID(String UUID);
    Optional<User> findTopByPhoneAndUserName(String phone, String userName);
    Optional<User> findByKakaoId(String id);
    Optional<User> findByNaverId(String id);
    List<User> findByStatus(int i);
}
