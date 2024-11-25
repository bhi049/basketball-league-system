package com.basketball.league.web;

import com.basketball.league.dto.PlayerDTO;
import com.basketball.league.dto.TeamDTO;
import com.basketball.league.model.TeamRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.basketball.league.model.Player;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    // Get all teams
    @GetMapping
    public List<TeamDTO> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(team -> new TeamDTO(
                        team.getId(),
                        team.getName(),
                        team.getCoach(),
                        team.getCity()
                ))
                .collect(Collectors.toList());
    }

    // Get a specific team by ID
    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
        return teamRepository.findById(id)
                .map(team -> new TeamDTO(
                        team.getId(),
                        team.getName(),
                        team.getCoach(),
                        team.getCity()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get players for a specific team
    @GetMapping("/{id}/players")
    public ResponseEntity<List<PlayerDTO>> getPlayersByTeam(@PathVariable Long id) {
        return teamRepository.findById(id)
            .map(team -> team.getPlayers().stream()
                .map(player -> new PlayerDTO(
                    player.getId(),
                    player.getFirstName(),
                    player.getLastName(),
                    player.getPosition(),
                    team.getId(),       // Add team ID
                    team.getName()      // Add team name
                ))
                .collect(Collectors.toList())
            )
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}

