package com.basketball.league;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.basketball.league.model.Team;
import com.basketball.league.model.TeamRepository;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  private final TeamRepository teamRepository;

  public HomeController(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
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
}
  

