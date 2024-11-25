package com.basketball.league;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

import com.basketball.league.model.GameRepository;
import com.basketball.league.model.Player;
import com.basketball.league.model.PlayerRepository;
import com.basketball.league.model.Team;
import com.basketball.league.model.TeamRepository;

@Configuration
public class TeamDataInitializer {

    @Bean
    public CommandLineRunner loadData(TeamRepository teamRepository, PlayerRepository playerRepository, GameRepository gameRepository) {
        return (args) -> {
            if (teamRepository.count() == 0) {
            
            // Initialize each team with players
            Team dragons = new Team("Dragons", "Kai Reynolds", "Emerald Bay");
            List<Player> dragonPlayers = List.of(
                new Player("John", "Davis", "Shooting Guard", dragons),
                new Player("Michael", "Lee", "Point Guard", dragons),
                new Player("Daniel", "Foster", "Small Forward", dragons),
                new Player("Chris", "Walker", "Power Forward", dragons),
                new Player("Kevin", "Jones", "Center", dragons),
                new Player("Steve", "Clark", "Shooting Guard", dragons),
                new Player("Brian", "Moore", "Point Guard", dragons),
                new Player("Jason", "Hall", "Small Forward", dragons),
                new Player("Thomas", "Young", "Power Forward", dragons),
                new Player("Robert", "King", "Center", dragons)
            );
            dragons.getPlayers().addAll(dragonPlayers);

            Team titans = new Team("Titans", "Ava Morrison", "Thunder Valley");
            List<Player> titanPlayers = List.of(
                new Player("Alex", "Brown", "Point Guard", titans),
                new Player("David", "Williams", "Shooting Guard", titans),
                new Player("Eric", "Johnson", "Small Forward", titans),
                new Player("Andrew", "Taylor", "Power Forward", titans),
                new Player("Anthony", "Green", "Center", titans),
                new Player("Peter", "Adams", "Shooting Guard", titans),
                new Player("James", "White", "Point Guard", titans),
                new Player("Patrick", "Robinson", "Small Forward", titans),
                new Player("Kyle", "Martin", "Power Forward", titans),
                new Player("Ethan", "Scott", "Center", titans)
            );
            titans.getPlayers().addAll(titanPlayers);

            Team falcons = new Team("Falcons", "Jordan Blake", "Skyline City");
            List<Player> falconPlayers = List.of(
                new Player("Henry", "Carter", "Point Guard", falcons),
                new Player("Ryan", "Lewis", "Shooting Guard", falcons),
                new Player("Samuel", "Clark", "Small Forward", falcons),
                new Player("Charles", "Miller", "Power Forward", falcons),
                new Player("Brandon", "Wilson", "Center", falcons),
                new Player("Edward", "Turner", "Shooting Guard", falcons),
                new Player("Adam", "Harris", "Point Guard", falcons),
                new Player("Justin", "Parker", "Small Forward", falcons),
                new Player("George", "Thompson", "Power Forward", falcons),
                new Player("Oliver", "Brooks", "Center", falcons)
            );
            falcons.getPlayers().addAll(falconPlayers);

            Team thunderhawks = new Team("Thunderhawks", "Zane Hawkins", "Storm City");
            List<Player> thunderhawksPlayers = List.of(
                new Player("Tyler", "Evans", "Point Guard", thunderhawks),
                new Player("Joshua", "Ward", "Shooting Guard", thunderhawks),
                new Player("Nicholas", "Cook", "Small Forward", thunderhawks),
                new Player("Matthew", "Morgan", "Power Forward", thunderhawks),
                new Player("Benjamin", "Hill", "Center", thunderhawks),
                new Player("Elijah", "Murphy", "Shooting Guard", thunderhawks),
                new Player("Christopher", "Phillips", "Point Guard", thunderhawks),
                new Player("Caleb", "Rogers", "Small Forward", thunderhawks),
                new Player("Nathan", "Cooper", "Power Forward", thunderhawks),
                new Player("Logan", "Bailey", "Center", thunderhawks)
            );
            thunderhawks.getPlayers().addAll(thunderhawksPlayers);

            Team silverbacks = new Team("Silverbacks", "Maya Torres", "Granite Hills");
            List<Player> silverbacksPlayers = List.of(
                new Player("Aaron", "Reed", "Point Guard", silverbacks),
                new Player("Austin", "Powell", "Shooting Guard", silverbacks),
                new Player("Jacob", "Collins", "Small Forward", silverbacks),
                new Player("Zachary", "Stewart", "Power Forward", silverbacks),
                new Player("Alexander", "Ross", "Center", silverbacks),
                new Player("Jack", "Carter", "Shooting Guard", silverbacks),
                new Player("Dylan", "Bell", "Point Guard", silverbacks),
                new Player("Lucas", "Fisher", "Small Forward", silverbacks),
                new Player("Elijah", "Ward", "Power Forward", silverbacks),
                new Player("Gabriel", "Reed", "Center", silverbacks)
            );
            silverbacks.getPlayers().addAll(silverbacksPlayers);

            Team raptors = new Team("Raptors", "Liam Carter", "Riverbend");
            List<Player> raptorsPlayers = List.of(
                new Player("Max", "Bennett", "Point Guard", raptors),
                new Player("Jordan", "Sanders", "Shooting Guard", raptors),
                new Player("Gavin", "Hughes", "Small Forward", raptors),
                new Player("Owen", "Kelly", "Power Forward", raptors),
                new Player("Sebastian", "Flores", "Center", raptors),
                new Player("Caleb", "Diaz", "Shooting Guard", raptors),
                new Player("Tyler", "Long", "Point Guard", raptors),
                new Player("Levi", "Myers", "Small Forward", raptors),
                new Player("Hunter", "Bryant", "Power Forward", raptors),
                new Player("Carter", "Anderson", "Center", raptors)
            );
            raptors.getPlayers().addAll(raptorsPlayers);

            Team cyclones = new Team("Cyclones", "Brianna Vance", "Windy Plains");
            List<Player> cyclonesPlayers = List.of(
                new Player("Wyatt", "Foster", "Point Guard", cyclones),
                new Player("Isaac", "Powell", "Shooting Guard", cyclones),
                new Player("Luke", "Russell", "Small Forward", cyclones),
                new Player("Jayden", "Simmons", "Power Forward", cyclones),
                new Player("Lucas", "Torres", "Center", cyclones),
                new Player("Mason", "Richardson", "Shooting Guard", cyclones),
                new Player("Logan", "Fisher", "Point Guard", cyclones),
                new Player("Dominic", "Hayes", "Small Forward", cyclones),
                new Player("Oliver", "Peterson", "Power Forward", cyclones),
                new Player("Cameron", "Morgan", "Center", cyclones)
            );
            cyclones.getPlayers().addAll(cyclonesPlayers);

            Team mustangs = new Team("Mustangs", "Blake Johnson", "Iron Ridge");
            List<Player> mustangsPlayers = List.of(
                new Player("Ethan", "Perry", "Point Guard", mustangs),
                new Player("James", "Jenkins", "Shooting Guard", mustangs),
                new Player("Connor", "Gibson", "Small Forward", mustangs),
                new Player("Caleb", "Gray", "Power Forward", mustangs),
                new Player("Jonathan", "Barnes", "Center", mustangs),
                new Player("Evan", "Allen", "Shooting Guard", mustangs),
                new Player("Daniel", "Murphy", "Point Guard", mustangs),
                new Player("Samuel", "Diaz", "Small Forward", mustangs),
                new Player("Michael", "Price", "Power Forward", mustangs),
                new Player("Ryan", "Ward", "Center", mustangs)
            );
            mustangs.getPlayers().addAll(mustangsPlayers);

            Team comets = new Team("Comets", "Olivia Green", "Celestial Bay");
            List<Player> cometsPlayers = List.of(
                new Player("Noah", "Jenkins", "Point Guard", comets),
                new Player("Hunter", "Coleman", "Shooting Guard", comets),
                new Player("Dylan", "Bryant", "Small Forward", comets),
                new Player("Logan", "Reed", "Power Forward", comets),
                new Player("Mason", "Patterson", "Center", comets),
                new Player("Aiden", "Scott", "Shooting Guard", comets),
                new Player("Jackson", "Brooks", "Point Guard", comets),
                new Player("Carter", "Sanders", "Small Forward", comets),
                new Player("Christian", "Bell", "Power Forward", comets),
                new Player("Isaac", "Cook", "Center", comets)
            );
            comets.getPlayers().addAll(cometsPlayers);

            Team phoenix = new Team("Phoenix", "Jackson Brooks", "Sunrise Coast");
            List<Player> phoenixPlayers = List.of(
                new Player("Henry", "Green", "Point Guard", phoenix),
                new Player("David", "Anderson", "Shooting Guard", phoenix),
                new Player("Jack", "Nelson", "Small Forward", phoenix),
                new Player("Christopher", "Lee", "Power Forward", phoenix),
                new Player("Sebastian", "Wright", "Center", phoenix),
                new Player("Benjamin", "Perry", "Shooting Guard", phoenix),
                new Player("Jackson", "Cooper", "Point Guard", phoenix),
                new Player("Lucas", "Hernandez", "Small Forward", phoenix),
                new Player("Samuel", "Mitchell", "Power Forward", phoenix),
                new Player("Ryan", "Lewis", "Center", phoenix)
            );
            phoenix.getPlayers().addAll(phoenixPlayers);

            // Save teams (players will also be saved due to CascadeType.ALL)
            teamRepository.saveAll(Arrays.asList(dragons, titans, falcons, thunderhawks, silverbacks, raptors, cyclones, mustangs, comets, phoenix));
            }
        };
    }
}
