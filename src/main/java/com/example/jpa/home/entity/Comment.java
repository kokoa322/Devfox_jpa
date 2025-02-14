package com.example.jpa.home.entity;

import com.example.jpa.home.entity.Board;
import lombok.Data;

import javax.persistence.*;
@Entity
@Table(name = "comments")
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 댓글 ID

    @Column(nullable = false)  // 댓글 내용은 필수로 입력해야 함
    private String content;  // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)  // 게시글은 필수
    private Board board;  // 해당 댓글이 속한 게시글

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // 유저는 필수
    private User user;  // 댓글 작성자 (유저)

    public Comment() {}
}
