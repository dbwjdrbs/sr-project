package com.springboot.gameResult.repository;

import com.springboot.gameResult.entity.GameResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameResultRepository extends JpaRepository<GameResult, Long> {
}
