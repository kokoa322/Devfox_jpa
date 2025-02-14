package com.example.jpa.home.config;

import com.example.jpa.home.entity.Board;
import com.example.jpa.home.entity.User;
import com.example.jpa.home.repository.BoardRepository;
import com.example.jpa.home.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public DataInitializer(UserRepository userRepository, BoardRepository boardRepository) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 유저 1명 생성
        User user = new User();
        user.setUsername("admin");
        user.setEmail("admin@example.com");
        user.setPassword("password");
        userRepository.save(user);

        // 300개의 게시글 생성
        IntStream.range(0, 300).forEach(i -> {
            Board board = new Board();
            board.setTitle("게시글 " + (i + 1));
            board.setContent("게시글 내용 " + (i + 1));
            board.setAuthor(user.getUsername());
            board.setUser(user); // 작성자는 방금 저장한 유저

            // 게시글 저장
            boardRepository.save(board);
        });
    }
}
