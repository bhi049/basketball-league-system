package com.basketball.league.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basketball.league.dto.StandingsDTO;
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

  public List<StandingsDTO> getStandings() {
    List<Team> teams = teamRepository.findAll();
    List<StandingsDTO> standings = new ArrayList<>();

    for (Team team : teams) {
      int wins = 0;
      int losses = 0;
      int scored = 0;
      int conceded = 0;

      // Calculate wins, losses, scored, and conceded for home games
      List<Game> homeGames = gameRepository.findByHomeTeam(team);
      for (Game game : homeGames) {
        scored += game.getHomeTeamScore();
        conceded += game.getAwayTeamScore();
        if (game.getHomeTeamScore() > game.getAwayTeamScore()) {
          wins++;
        } else {
          losses++;
        }
      }

      // Calculate wins, losses, scored, and conceded for away games
      List<Game> awayGames = gameRepository.findByAwayTeam(team);
      for (Game game : awayGames) {
        scored += game.getAwayTeamScore();
        conceded += game.getHomeTeamScore();
        if (game.getAwayTeamScore() > game.getHomeTeamScore()) {
          wins++;
        } else {
          losses++;
        }
      }

      // Create a StandingsDTO for the team
      standings.add(new StandingsDTO(team.getName(), wins, losses, scored, conceded));
    }

    // Sort standings by wins and then by score differential
    standings.sort(Comparator.comparingInt(StandingsDTO::getWins).reversed()
        .thenComparingInt(StandingsDTO::getDifferential).reversed());

    return standings;
  }
}
