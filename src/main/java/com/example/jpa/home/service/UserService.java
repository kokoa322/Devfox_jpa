package com.example.jpa.home.service;

import com.example.jpa.home.dto.BoardDTO;
import com.example.jpa.home.dto.CommentDTO;
import com.example.jpa.home.dto.UserDTO;
import com.example.jpa.home.entity.Board;
import com.example.jpa.home.entity.Comment;
import com.example.jpa.home.entity.User;
import com.example.jpa.home.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> selectAllLazy(Long id) {
        System.out.println("----- N+1 lazy -----");

        List<User> users = userRepository.findAll();  // 모든 유저 조회
        List<UserDTO> userDTOs = new ArrayList<>();

        for (User user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());

            // BoardDTO 리스트로 변환
            List<BoardDTO> boardDTOs = new ArrayList<>();
            for (Board board : user.getBoards()) {
                BoardDTO boardDTO = new BoardDTO();
                boardDTO.setId(board.getId());
                boardDTO.setTitle(board.getTitle());
                boardDTO.setContent(board.getContent());

                // CommentDTO 리스트로 변환 (선택적으로 가져올 수 있음)
                List<CommentDTO> commentDTOs = new ArrayList<>();
                for (Comment comment : board.getComments()) {
                    CommentDTO commentDTO = new CommentDTO();
                    commentDTO.setId(comment.getId());
                    commentDTO.setContent(comment.getContent());
                    commentDTOs.add(commentDTO);
                }

                // 댓글 정보를 포함하여 BoardDTO에 설정
                boardDTO.setComments(commentDTOs);
                boardDTOs.add(boardDTO);
            }

            // BoardDTO 목록을 UserDTO에 설정
            userDTO.setBoards(boardDTOs);
            userDTOs.add(userDTO);
        }

        return userDTOs;
    }

    public List<UserDTO> selectAllfetch(Long id) {
        System.out.println("----- N+1 fetch -----");
        List<User> users = userRepository.findAllWithBoards();

        // user ID 리스트 추출
        List<Long> userIds = users.stream().map(User::getId).collect(Collectors.toList());

        if (!userIds.isEmpty()) {
            List<Board> boards = userRepository.findAllWithComments(userIds);

            // User에 Board와 Comment 연결
            Map<Long, List<Board>> boardMap = boards.stream().collect(Collectors.groupingBy(board -> board.getUser().getId()));
            for (User user : users) {
                user.setBoards(boardMap.getOrDefault(user.getId(), new ArrayList<>()));
            }
        }

        return users.stream().map(UserDTO::fromEntity).collect(Collectors.toList());
    }
}
