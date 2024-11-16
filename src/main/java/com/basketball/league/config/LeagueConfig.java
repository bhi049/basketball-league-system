package com.basketball.league.config;

import com.basketball.league.service.GameService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@DependsOn("teamDataInitializer")
public class LeagueConfig {

    private final GameService gameService;

    public LeagueConfig(GameService gameService) {
        this.gameService = gameService;
    }

    @Bean
    public CommandLineRunner initializeGames() {
        return args -> {
            gameService.scheduleGames(); // Initializes the games
        };
    }
}
