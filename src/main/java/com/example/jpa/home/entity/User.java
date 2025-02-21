package com.example.jpa.home.entity;

import com.example.jpa.home.entity.Board;
import com.example.jpa.home.entity.Comment;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "users")
@Data
public class User {

    @Id // 기본 키 필드 지정
    //기본 키(Primary Key)를 자동으로 생성하는 방법을 지정하는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)  // 사용자 이름은 필수, 유일값, 길이 제한
    private String username;  // 사용자 이름 (ID)

    @Column(nullable = false, unique = true)  // 이메일은 필수, 유일값
    private String email;  // 이메일

    @Column(nullable = false)  // 비밀번호는 필수
    private String password;  // 비밀번호

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)  // 작성한 게시글 목록
    private List<Board> boards;  // 작성한 게시글 목록

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)  // 작성한 댓글 목록
    private List<Comment> comments;  // 작성한 댓글 목록

    public User() {}
}
