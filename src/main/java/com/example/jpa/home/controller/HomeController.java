package com.example.jpa.home.controller;

import com.example.jpa.home.dto.BoardDTO;
import com.example.jpa.home.dto.UserDTO;
import com.example.jpa.home.entity.Board;
import com.example.jpa.home.entity.Comment;
import com.example.jpa.home.entity.User;
import com.example.jpa.home.service.BoardService;
import com.example.jpa.home.service.CommentService;
import com.example.jpa.home.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String home(@RequestParam(defaultValue = "0") int page,  // 기본값으로 0번 페이지
                       @RequestParam(defaultValue = "5") int size,  // 한 페이지에 5개 게시글
                       Model model) {
        Page<Board> boardPage = boardService.getAllBoards(page, size);

        model.addAttribute("boards", boardPage.getContent());  // 게시글 리스트
        model.addAttribute("currentPage", page);                // 현재 페이지
        model.addAttribute("totalPages", boardPage.getTotalPages());  // 전체 페이지 수
        model.addAttribute("totalItems", boardPage.getTotalElements());  // 전체 게시글 수
        return "jsp/home";
    }

    @GetMapping("/test")
    public String test() {

        List<UserDTO> users_lazy = userService.selectAllLazy(1L);

        System.out.println("----- N+1 lazy -----");
        for (UserDTO user : users_lazy) {
            for (BoardDTO board : user.getBoards()) {
                // 게시글마다 별도의 쿼리 발생
                System.out.println(board.getTitle());
            }
        }

        List<UserDTO> users_fetch = userService.selectAllfetch(1L);

        System.out.println("----- N+1 fetch -----");
        for (UserDTO user : users_lazy) {
            for (BoardDTO board : user.getBoards()) {
                // 게시글마다 별도의 쿼리 발생
                System.out.println(board.getTitle());
            }
        }
        return "jsp/home";
    }
}
