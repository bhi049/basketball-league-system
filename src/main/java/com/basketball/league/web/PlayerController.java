package com.basketball.league.web;

import com.basketball.league.dto.PlayerDTO;
import com.basketball.league.model.Player;
import com.basketball.league.model.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    // Get all players
    @GetMapping
    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll().stream()
            .map(player -> new PlayerDTO(
                player.getId(),
                player.getFirstName(),
                player.getLastName(),
                player.getPosition()
            ))
            .collect(Collectors.toList());
    }

    // Get a specific player by ID
    @GetMapping("/{id}")
    public PlayerDTO getPlayerById(@PathVariable Long id) {
        return playerRepository.findById(id)
            .map(player -> new PlayerDTO(
                player.getId(),
                player.getFirstName(),
                player.getLastName(),
                player.getPosition()
            ))
            .orElse(null); // Return null or handle properly with an exception
    }
}
