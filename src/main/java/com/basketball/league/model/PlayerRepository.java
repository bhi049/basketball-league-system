package com.basketball.league.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeam_Id(Long teamId); // Find all players in a specific team
}
