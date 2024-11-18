package com.basketball.league.web;

import com.basketball.league.dto.PlayerDTO;
import com.basketball.league.model.Team;
import com.basketball.league.model.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        return teamRepository.findById(id)
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
                    player.getPosition()
                ))
                .collect(Collectors.toList())
            )
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
