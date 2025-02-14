package com.example.jpa.home.repository;
import com.example.jpa.home.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BoardRepository extends JpaRepository<Board, Long> {
}
