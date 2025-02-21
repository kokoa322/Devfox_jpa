package com.example.jpa.home.service;

import com.example.jpa.home.entity.Board;
import com.example.jpa.home.entity.User;
import com.example.jpa.home.repository.BoardRepository;
import com.example.jpa.home.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    // 페이징 처리된 게시글 목록 반환
    public Page<Board> getAllBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);  // 페이지 번호와 크기를 받아서 Pageable 객체 생성
        return boardRepository.findAll(pageable);  // 페이징 처리된 게시글 목록 반환
    }



}
