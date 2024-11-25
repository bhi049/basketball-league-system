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
        // Get the currently authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); // This gets the username of the logged-in user
        User user = userRepository.findByUsername(username).orElse(null);

        // Debugging logs
    System.out.println("Logged-in username: " + username);
    if (user != null) {
        System.out.println("User role: " + user.getRole());
        System.out.println("User favourite team: " + (user.getFavouriteTeam() != null ? user.getFavouriteTeam().getName() : "None"));
        System.out.println("User favourite player: " + (user.getFavouritePlayer() != null ? user.getFavouritePlayer().getFirstName() : "None"));
    } else {
        System.out.println("No user found in the database for the username.");
    }


        // Add attributes to the model
        model.addAttribute("user", user); // The current user
        model.addAttribute("teams", teamRepository.findAll()); // All teams for dropdowns
        model.addAttribute("players", playerRepository.findAll()); // All players for dropdowns
        model.addAttribute("roles", auth.getAuthorities());

        return "account"; // Return the view name (account.html)
    }

    @PostMapping("/account/favourite-team")
    public String saveFavouriteTeam(@RequestParam Long team, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            Team favouriteTeam = teamRepository.findById(team).orElse(null);
            user.setFavouriteTeam(favouriteTeam);
            userRepository.save(user);
        }

        return "redirect:/account";
    }

    @PostMapping("/account/favourite-player")
    public String saveFavouritePlayer(@RequestParam Long player, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            Player favouritePlayer = playerRepository.findById(player).orElse(null);
            user.setFavouritePlayer(favouritePlayer);
            userRepository.save(user);
        }

        return "redirect:/account";
    }
    @PostMapping("/account/favourite-team/clear")
public String clearFavouriteTeam() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User user = userRepository.findByUsername(username).orElse(null);

    if (user != null) {
        user.setFavouriteTeam(null); // Clear the favorite team
        userRepository.save(user);
    }

    return "redirect:/account"; // Redirect back to the account page
}

@PostMapping("/account/favourite-player/clear")
public String clearFavouritePlayer() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    User user = userRepository.findByUsername(username).orElse(null);

    if (user != null) {
        user.setFavouritePlayer(null); // Clear the favorite player
        userRepository.save(user);
    }

    return "redirect:/account"; // Redirect back to the account page
}

}
