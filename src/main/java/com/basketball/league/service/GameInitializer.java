package com.basketball.league.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GameInitializer implements CommandLineRunner {

    private final GameService gameService;

    public GameInitializer(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) {
        if (gameService.hasTeams()) { // Check if teams exist
            gameService.scheduleGames();
            System.out.println("Games have been initialized and saved to the database.");
        } else {
            System.out.println("No teams found. Skipping game initialization.");
        }
    }
}