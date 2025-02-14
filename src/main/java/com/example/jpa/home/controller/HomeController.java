package com.example.jpa.home.controller;

import com.example.jpa.home.entity.Board;
import com.example.jpa.home.entity.Comment;
import com.example.jpa.home.service.BoardService;
import com.example.jpa.home.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private BoardService boardService;

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

}
