package com.example.jpa.home.repository;

import com.example.jpa.home.entity.Board;
import com.example.jpa.home.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT DISTINCT u FROM User u JOIN FETCH u.boards")
    List<User> findAllWithBoards();

    @Query("SELECT DISTINCT b FROM Board b JOIN FETCH b.comments WHERE b.user.id IN :userIds")
    List<Board> findAllWithComments(@Param("userIds") List<Long> userIds);

    User findByUsername(String username);  // 사용자 이름으로 찾기
    User findByEmail(String email);  // 이메일로 찾기
}
