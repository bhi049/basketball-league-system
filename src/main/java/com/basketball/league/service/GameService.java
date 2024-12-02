package com.basketball.league.service;

import java.util.ArrayList;
import java.util.Collections;
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
        if (gameRepository.count() > 0) {
            // Games have already been scheduled, so skip initialization
            return;
        }

        List<Team> teams = teamRepository.findAll();
        List<Game> gamesToSchedule = new ArrayList<>();

        // Generate all possible games (home vs away) once
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Team homeTeam = teams.get(i);
                Team awayTeam = teams.get(j);

                Game game = new Game();
                game.setHomeTeam(homeTeam);
                game.setAwayTeam(awayTeam);

                gamesToSchedule.add(game);
            }
        }

        // Shuffle the games list to randomize the order
        Collections.shuffle(gamesToSchedule);

        // Generate randomized scores for each game and ensure no ties
        for (Game game : gamesToSchedule) {
            int homeBase = 80 + (int) (Math.random() * 20); // Base: 80–100
            int awayBase = 75 + (int) (Math.random() * 20); // Base: 75–95
            int homeRandomFactor = (int) (Math.random() * 15) - 7; // Random: -7 to +7
            int awayRandomFactor = (int) (Math.random() * 15) - 7; // Random: -7 to +7

            int homeScore = homeBase + homeRandomFactor;
            int awayScore = awayBase + awayRandomFactor;

            // Random chance to favor the away team slightly
            if (Math.random() > 0.5) {
                homeScore -= (int) (Math.random() * 5);
                awayScore += (int) (Math.random() * 5);
            }

            // Ensure no ties
            if (homeScore == awayScore) {
                if (Math.random() > 0.5) {
                    homeScore++;
                } else {
                    awayScore++;
                }
            }

            game.setHomeTeamScore(Math.max(50, homeScore)); // Ensure minimum score of 50
            game.setAwayTeamScore(Math.max(50, awayScore)); // Ensure minimum score of 50

            // Save the game to the repository
            gameRepository.save(game);
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
            StandingsDTO dto = new StandingsDTO(team.getName(), wins, losses, scored, conceded);
            standings.add(dto);
    
            // Debugging: Log individual team stats
            System.out.println("Team: " + team.getName() +
                    " | Wins: " + wins +
                    " | Losses: " + losses +
                    " | Scored: " + scored +
                    " | Conceded: " + conceded +
                    " | Differential: " + dto.getDifferential());
        }
    
        // Sort standings by wins descending, then by differential descending
        standings.sort(Comparator.comparingInt(StandingsDTO::getWins).reversed()
                .thenComparingInt(StandingsDTO::getDifferential).reversed());
    
        // Debugging: Log the sorted order
        System.out.println("Sorted Standings:");
        standings.forEach(dto -> {
            System.out.println("Team: " + dto.getTeamName() +
                    " | Wins: " + dto.getWins() +
                    " | Differential: " + dto.getDifferential());
        });
    
        return standings;
    }
    
    
}
