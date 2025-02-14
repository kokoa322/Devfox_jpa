package com.example.jpa.home.service;

import com.example.jpa.home.entity.Board;
import com.example.jpa.home.entity.Comment;
import com.example.jpa.home.entity.User;
import com.example.jpa.home.repository.BoardRepository;
import com.example.jpa.home.repository.CommentRepository;
import com.example.jpa.home.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    // 댓글 저장
    public Comment saveComment(Long boardId, String content, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;  // 유저가 없으면 null 반환
        }

        Board board = boardRepository.findById(boardId).orElse(null);
        if (board == null) {
            return null;  // 게시글이 없으면 null 반환
        }

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setBoard(board);

        return commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
