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

    @GetMapping("/account")
    public String accountPage(Model model) {
        // Get currently logged-in user's username
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        // Fetch user from the database
        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            System.out.println("User not found for username: " + username);
        } else {
            System.out.println("User found: " + user.getUsername());
            System.out.println("Favourite Team: " + (user.getFavouriteTeam() != null ? user.getFavouriteTeam().getName() : "None"));
            System.out.println("Favourite Player: " + (user.getFavouritePlayer() != null ? user.getFavouritePlayer().getFirstName() : "None"));
        }

        // Add attributes to the model
        model.addAttribute("user", user);
        model.addAttribute("teams", teamRepository.findAll());
        model.addAttribute("players", playerRepository.findAll());

        return "account";
    }

    @PostMapping("/account/favourite-team")
    public String saveFavouriteTeam(@RequestParam Long team, Model model) {
        // Get currently logged-in user's username
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        // Fetch user from the database
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            // Find and set the favourite team
            Team favouriteTeam = teamRepository.findById(team).orElse(null);
            if (favouriteTeam != null) {
                user.setFavouriteTeam(favouriteTeam);
                userRepository.save(user);
                System.out.println("Favourite Team updated: " + favouriteTeam.getName());
            } else {
                System.out.println("Team not found with ID: " + team);
            }
        } else {
            System.out.println("User not found for username: " + username);
        }

        return "redirect:/account";
    }

    @PostMapping("/account/favourite-player")
    public String saveFavouritePlayer(@RequestParam Long player, Model model) {
        // Get currently logged-in user's username
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        // Fetch user from the database
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            // Find and set the favourite player
            Player favouritePlayer = playerRepository.findById(player).orElse(null);
            if (favouritePlayer != null) {
                user.setFavouritePlayer(favouritePlayer);
                userRepository.save(user);
                System.out.println("Favourite Player updated: " + favouritePlayer.getFirstName() + " " + favouritePlayer.getLastName());
            } else {
                System.out.println("Player not found with ID: " + player);
            }
        } else {
            System.out.println("User not found for username: " + username);
        }

        return "redirect:/account";
    }
}
