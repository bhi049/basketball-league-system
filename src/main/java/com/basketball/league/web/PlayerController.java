package com.basketball.league.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basketball.league.model.Player;
import com.basketball.league.model.PlayerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

  @Autowired
  private PlayerRepository playerRepository;

  // Get all players
  @GetMapping
  public List<Player> getAllPlayers() {
    return playerRepository.findAll();
  }

  // Get a specific player by ID
  @GetMapping("/{id}")
  public Player getPlayerById(@PathVariable Long id) {
    return playerRepository.findById(id).orElse(null);
  }
  
  
}
