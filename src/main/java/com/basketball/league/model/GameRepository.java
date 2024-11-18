package com.basketball.league.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
  List<Game> findByHomeTeam(Team homeTeam); // Fetch games by home team
  List<Game> findByAwayTeam(Team awayTeam); // Fetch games by away team
}