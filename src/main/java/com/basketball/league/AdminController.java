package com.basketball.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.basketball.league.model.Player;
import com.basketball.league.model.PlayerRepository;
import com.basketball.league.model.Team;
import com.basketball.league.model.TeamRepository;
import com.basketball.league.model.Game;
import com.basketball.league.model.GameRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private PlayerRepository playerRepository;

  @Autowired
  private TeamRepository teamRepository;

  @Autowired
  private GameRepository gameRepository;

  @GetMapping
  public String adminPage() {
    return "admin"; // Main admin dashboard
  }

  // --- TEAMS CRUD ---
  @GetMapping("/teams")
  public String manageTeams(Model model) {
    model.addAttribute("teams", teamRepository.findAll());
    return "admin-teams";
  }

  @GetMapping("/teams/add")
  public String addTeamForm(Model model) {
    model.addAttribute("team", new Team());
    return "edit-team";
  }

  @GetMapping("/teams/edit/{id}")
  public String editTeamForm(@PathVariable Long id, Model model) {
    Team team = teamRepository.findById(id).orElse(new Team());
    model.addAttribute("team", team);
    return "edit-team";
  }

  @PostMapping("/teams/save")
  public String saveTeam(@ModelAttribute Team team) {
    teamRepository.save(team);
    return "redirect:/admin/teams";
  }

  @GetMapping("/teams/delete/{id}")
  public String deleteTeam(@PathVariable Long id) {
    teamRepository.deleteById(id);
    return "redirect:/admin/teams";
  }

  // --- PLAYERS CRUD ---
  @GetMapping("/players")
  public String managePlayers(Model model) {
    model.addAttribute("players", playerRepository.findAll());
    return "admin-players";
  }

  @GetMapping("/players/add")
  public String addPlayerForm(Model model) {
    model.addAttribute("player", new Player());
    model.addAttribute("teams", teamRepository.findAll()); // To select team
    return "edit-player";
  }

  @GetMapping("/players/edit/{id}")
  public String editPlayerForm(@PathVariable Long id, Model model) {
    Player player = playerRepository.findById(id).orElse(new Player());
    model.addAttribute("player", player);
    model.addAttribute("teams", teamRepository.findAll());
    return "edit-player";
  }

  @PostMapping("/players/save")
  public String savePlayer(@ModelAttribute Player player) {
    playerRepository.save(player);
    return "redirect:/admin/players";
  }

  @GetMapping("/players/delete/{id}")
  public String deletePlayer(@PathVariable Long id) {
    playerRepository.deleteById(id);
    return "redirect:/admin/players";
  }

  // --- GAMES CRUD ---
  @GetMapping("/games")
  public String manageGames(Model model) {
    model.addAttribute("games", gameRepository.findAll());
    return "admin-games";
  }

  @GetMapping("/games/add")
  public String addGameForm(Model model) {
    model.addAttribute("game", new Game());
    model.addAttribute("teams", teamRepository.findAll()); // To select home/away teams
    return "edit-game";
  }

  @GetMapping("/games/edit/{id}")
  public String editGameForm(@PathVariable Long id, Model model) {
    Game game = gameRepository.findById(id).orElse(new Game());
    model.addAttribute("game", game);
    model.addAttribute("teams", teamRepository.findAll());
    return "edit-game";
  }

  @PostMapping("/games/save")
  public String saveGame(@ModelAttribute Game game) {
    gameRepository.save(game);
    return "redirect:/admin/games";
  }

  @GetMapping("/games/delete/{id}")
  public String deleteGame(@PathVariable Long id) {
    gameRepository.deleteById(id);
    return "redirect:/admin/games";
  }
}
