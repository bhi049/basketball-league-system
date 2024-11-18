package com.basketball.league.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basketball.league.model.Game;
import com.basketball.league.model.GameRepository;
import com.basketball.league.model.Team;
import com.basketball.league.model.TeamRepository;

import jakarta.transaction.Transactional;

@Service
public class GameService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private GameRepository gameRepository;

    /**
     * Check if teams exist in the database.
     *
     * @return true if teams exist, false otherwise.
     */
    public boolean hasTeams() {
        return teamRepository.count() > 0;
    }

    /**
     * Schedule games between all teams.
     */
    @Transactional
    public void scheduleGames() {
        List<Team> teams = teamRepository.findAll();

        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Team homeTeam = teams.get(i);
                Team awayTeam = teams.get(j);

                // Create a new Game instance
                Game game = new Game();
                game.setHomeTeam(homeTeam);
                game.setAwayTeam(awayTeam);

                // Generate consistent scores with randomness based on team IDs
                int combinedIdSum = homeTeam.getId().intValue() + awayTeam.getId().intValue();
                int homeScore = (combinedIdSum * 3 % 30) + 75; // Range: 75-104
                int awayScore = (combinedIdSum * 5 % 30) + 70; // Range: 70-99

                // Slight chance for away team to win
                if (combinedIdSum % 2 == 0) {
                    int temp = homeScore;
                    homeScore = awayScore;
                    awayScore = temp;
                }

                game.setHomeTeamScore(homeScore);
                game.setAwayTeamScore(awayScore);

                // Save the game to the repository
                gameRepository.save(game);
            }
        }
    }
}
