package com.basketball.league.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.basketball.league.dto.StandingsDTO;
import com.basketball.league.service.GameService;

@Controller
public class StandingsController {

    @Autowired
    private GameService gameService;

    @GetMapping("/standings")
    public String getStandings(Model model) {
        List<StandingsDTO> standings = gameService.getStandings();
    
        // Log the standings to verify order
        standings.forEach(standing -> 
            System.out.println("Team: " + standing.getTeamName() + ", Wins: " 
                + standing.getWins() + ", Differential: " + standing.getDifferential()));
    
        model.addAttribute("standings", standings);
        return "standings";
    }
}
