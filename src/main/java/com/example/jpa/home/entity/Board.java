package com.example.jpa.home.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
@Table(name = "boards")  // 명시적인 테이블 이름 지정
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 게시글 ID

    @Column(nullable = false, length = 100)  // 제목은 null이 아니며 길이를 100으로 제한
    private String title;  // 게시글 제목

    @Column(nullable = false)  // 내용은 null이 아니도록 설정
    private String content;  // 게시글 내용

    @Column(nullable = false, length = 50)  // 작성자는 null이 아니며 길이를 50으로 제한
    private String author;  // 작성자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)  // 유저는 필수, null 허용 안 함
    private User user;  // 게시글 작성자 (유저)

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)  // 댓글 삭제 시 게시글도 함께 삭제
    private List<Comment> comments;  // 게시글에 달린 댓글들

    // 기본 생성자
    public Board() {}
}
