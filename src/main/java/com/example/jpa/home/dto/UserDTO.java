package com.example.jpa.home.dto;

import com.example.jpa.home.entity.User;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private List<BoardDTO> boards;

    // 엔티티 -> DTO 변환 메서드
    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());

        // Board 엔티티 리스트 -> BoardDTO 리스트 변환
        if (user.getBoards() != null) {
            List<BoardDTO> boardDTOs = user.getBoards().stream()
                    .map(BoardDTO::fromEntity)  // BoardDTO 변환 메서드 호출
                    .collect(Collectors.toList());
            dto.setBoards(boardDTOs);
        }

        return dto;
    }
}

