package com.basketball.league.web;

import com.basketball.league.model.Player;
import com.basketball.league.model.Team;
import com.basketball.league.model.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    // Get all teams
    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    // Get a specific team by ID
    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    // Get players for a specific team
    @GetMapping("/{id}/players")
    public List<Player> getPlayersByTeam(@PathVariable Long id) {
        Team team = teamRepository.findById(id).orElse(null);
        return team != null ? team.getPlayers() : null;
    } 
}
