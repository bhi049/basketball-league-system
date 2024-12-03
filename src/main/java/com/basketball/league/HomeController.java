package com.basketball.league;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import com.basketball.league.model.Game;
import com.basketball.league.model.GameRepository;
import com.basketball.league.model.Player;
import com.basketball.league.model.PlayerRepository;
import com.basketball.league.model.Team;
import com.basketball.league.model.TeamRepository;
import com.basketball.league.model.User;
import com.basketball.league.model.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.basketball.league.service.EmailService;



@Controller
public class HomeController {

  private final TeamRepository teamRepository;
  private final PlayerRepository playerRepository;
  private final GameRepository gameRepository;
  private final UserRepository userRepository;
  private final EmailService emailService;


  public HomeController(TeamRepository teamRepository, PlayerRepository playerRepository, GameRepository gameRepository, UserRepository userRepository, EmailService emailService) {
    this.teamRepository = teamRepository;
    this.playerRepository = playerRepository;
    this.gameRepository = gameRepository;
    this.userRepository = userRepository;
    this.emailService = emailService;
  }

  @GetMapping("/")
  public String home() {
      return "index"; // Maps to src/main/resources/templates/index.html
  }

  @GetMapping("/login")
  public String loginPage() {
      return "login"; // Maps to src/main/resources/templates/login.html
  }

  @GetMapping("/contact")
  public String contactPage() {
      return "contact";
  }
  
// Handle form submission
@PostMapping("/contact")
public String handleContactForm(
  @RequestParam("name") String name,
  @RequestParam("email") String email,
  @RequestParam("message") String message,
  Model model) {
    try {
        // Construct the email content
        String subject = "New Contact Message from " + name;
        String body = "You have received a new message from your website:\n\n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n\n" +
                "Message:\n" + message;

        // Send the email to yourself
        emailService.sendEmail("nikolas.kataja@gmail.com", subject, body);

        // Add success message to the model
        model.addAttribute("success", "Thank you for reaching out! Your message has been sent successfully.");
    } catch (Exception e) {
        // Log the error for debugging
        e.printStackTrace();

        // Add error message to the model
        model.addAttribute("error", "Oops! Something went wrong. Please try again.");
    }

    return "contact";
}

    // List of all teams
    @GetMapping("/teams")
    public String teams(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName(); // Get logged-in username
    User user = userRepository.findByUsername(username).orElse(null); // Fetch user

    List<Team> teams = teamRepository.findAll();
    model.addAttribute("teams", teams);
    model.addAttribute("user", user); // Add user to model
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

    // List of all players
    @GetMapping("/players")
    public String players(Model model) {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String username = auth.getName(); // Get logged-in username
      User user = userRepository.findByUsername(username).orElse(null); // Fetch user
  
      List<Player> players = playerRepository.findAll();
      model.addAttribute("players", players);
      model.addAttribute("user", user); // Add user to model
      return "players";
  }

    // Show games
    @GetMapping("/games")
    public String games(Model model) {
      List<Game> games = gameRepository.findAll();
      model.addAttribute("games", games);
      return "games";
  }


}
  

