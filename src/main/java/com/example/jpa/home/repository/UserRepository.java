package com.example.jpa.home.repository;

import com.example.jpa.home.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);  // 사용자 이름으로 찾기
    User findByEmail(String email);  // 이메일로 찾기
}
