package com.example.jpa.home.dto;

import com.example.jpa.home.entity.Board;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BoardDTO {
    private Long id;        // 게시글 ID
    private String title;   // 게시글 제목
    private String content; // 게시글 내용
    private List<CommentDTO> comments;  // 댓글 목록 (List<CommentDTO>)

    // 엔티티 -> DTO 변환 메서드
    public static BoardDTO fromEntity(Board board) {
        BoardDTO dto = new BoardDTO();
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());

        // Comment 엔티티 리스트 -> CommentDTO 리스트 변환
        if (board.getComments() != null) {
            List<CommentDTO> commentDTOs = board.getComments().stream()
                    .map(CommentDTO::fromEntity)  // CommentDTO 변환 메서드 호출
                    .collect(Collectors.toList());
            dto.setComments(commentDTOs);
        }

        return dto;
    }
}
