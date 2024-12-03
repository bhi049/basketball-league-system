package com.basketball.league.service;

import org.springframework.stereotype.Service;

import com.basketball.league.model.Team;
import com.basketball.league.model.TeamRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team updateTeam(Long teamId, Team updatedTeam) {
        Team existingTeam = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));

        // Retain existing players if not explicitly removed
        if (updatedTeam.getPlayers() == null || updatedTeam.getPlayers().isEmpty()) {
            updatedTeam.setPlayers(existingTeam.getPlayers());
        }

        // Retain existing logo if not explicitly updated
        if (updatedTeam.getLogoPath() == null) {
            updatedTeam.setLogoPath(existingTeam.getLogoPath());
        }

        // Copy other fields
        existingTeam.setName(updatedTeam.getName());

        return teamRepository.save(existingTeam);
    }
}

