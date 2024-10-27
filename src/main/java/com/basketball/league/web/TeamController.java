package com.basketball.league.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basketball.league.model.Team;
import com.basketball.league.model.TeamRepository;

@RestController
public class TeamController {
  private final TeamRepository teamRepository;

  public TeamController(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @GetMapping("/teams")
  public Iterable<Team> getTeams() {
    return teamRepository.findAll();
  } 
}
  

