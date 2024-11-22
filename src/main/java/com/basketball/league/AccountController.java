package com.basketball.league;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.basketball.league.model.Player;
import com.basketball.league.model.PlayerRepository;
import com.basketball.league.model.Team;
import com.basketball.league.model.TeamRepository;
import com.basketball.league.model.User;
import com.basketball.league.model.UserRepository;

@Controller
public class AccountController {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final UserRepository userRepository;

    public AccountController(TeamRepository teamRepository, PlayerRepository playerRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.userRepository = userRepository;
    }

    // Display the account page
    @GetMapping("/account")
    public String accountPage(Model model) {
        // Get the currently logged-in user's authentication object
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        // Retrieve the username from the authentication
        String username = auth.getName();
    
        // Fetch the user based on the username
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
          return "redirect:/login?error=true"; // Redirect to login if user is not found
      }
    
        // Add the user, teams, and players to the model
        model.addAttribute("user", user);
        model.addAttribute("username", username);
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("players", playerRepository.findAll());
    
        return "account"; // Renders account.html
    }
    // Handle form submission for updating preferences
    @PostMapping("/account")
    public String savePreferences(@RequestParam Long favTeam, @RequestParam Long favPlayer) {
        // Get the currently logged-in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        // Update the user's favorite team and player
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            Team team = teamRepository.findById(favTeam).orElse(null);
            Player player = playerRepository.findById(favPlayer).orElse(null);

            user.setFavouriteTeam(team);
            user.setFavouritPlayer(player);
            userRepository.save(user);
        }

        return "redirect:/account"; // Redirect back to the account page
    }
}
