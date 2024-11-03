package com.basketball.league;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.basketball.league.model.Player;
import com.basketball.league.model.PlayerRepository;
import com.basketball.league.model.Team;
import com.basketball.league.model.TeamRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

  private final TeamRepository teamRepository;
  private final PlayerRepository playerRepository;

  public HomeController(TeamRepository teamRepository, PlayerRepository playerRepository) {
    this.teamRepository = teamRepository;
    this.playerRepository = playerRepository;
  }

  // Homepage
  @GetMapping("/")
    public String home() {
      return "index";
    }

    // List of all teams
    @GetMapping("/teams")
    public String teams(Model model) {
      List<Team> teams = teamRepository.findAll();
      model.addAttribute("teams", teams);
      return "teams";
    }

    // Team details by ID
    @GetMapping("/teams/{id}")
    public String teamDetails(@PathVariable Long id, Model model) {
      Team team = teamRepository.findById(id).orElse(null);
      List<Player> players = playerRepository.findByTeam_Id(id);
      model.addAttribute("team", team);
      model.addAttribute("players", players);
      return "team-details";
    } 
}
  

