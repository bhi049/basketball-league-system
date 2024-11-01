package com.basketball.league.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Date;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByDate(Date date); // Find games on a specific date
}