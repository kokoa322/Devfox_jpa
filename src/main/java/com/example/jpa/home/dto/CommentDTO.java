package com.example.jpa.home.dto;

import com.example.jpa.home.entity.Comment;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;      // 댓글 ID
    private String content; // 댓글 내용

    // 엔티티 -> DTO 변환 메서드
    public static CommentDTO fromEntity(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        return dto;
    }
}
