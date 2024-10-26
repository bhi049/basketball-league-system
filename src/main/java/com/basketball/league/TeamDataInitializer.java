package com.basketball.league;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

import com.basketball.league.model.Player;
import com.basketball.league.model.PlayerRepository;
import com.basketball.league.model.Team;
import com.basketball.league.model.TeamRepository;

@Configuration
public class TeamDataInitializer {

  @Bean
  public CommandLineRunner loadData(TeamRepository teamRepository, PlayerRepository playerRepository) {
    return (args) -> {
      // All 10 teams
      Team dragons = new Team("Dragons", "Kai Reynolds", "Emerald Bay");
			Team titans = new Team("Titans", "Ava Morrison", "Thunder Valley");
			Team falcons = new Team("Falcons", "Jordan Blake", "Skyline City");
      Team thunderhawks = new Team("Thunderhawks", "Zane Hawkins", "Storm City");
      Team silverbacks = new Team("Silverbacks", "Maya Torres", "Granite Hills");
			Team raptors = new Team("Raptors", "Liam Carter", "Riverbend");
			Team cyclones = new Team("Cyclones", "Brianna Vance", "Windy Plains");
      Team mustangs = new Team("Mustangs", "Blake Johnson", "Iron Ridge");
      Team comets = new Team("Comets", "Olivia Green", "Celestial Bay");
			Team phoenix = new Team("Phoenix", "Jackson Brooks", "Sunrise Coast");

      // Save teams
      teamRepository.saveAll(Arrays.asList(dragons, titans, falcons, thunderhawks, silverbacks, raptors, cyclones, mustangs, comets, phoenix));

      // Players for each team
      playerRepository.saveAll(Arrays.asList(
        
        // Dragons
        new Player("John", "Davis", "Shooting Guard", dragons),
        new Player("Michael", "Lee", "Point Guard", dragons),
        new Player("Daniel", "Foster", "Small Forward", dragons),
        new Player("Chris", "Walker", "Power Forward", dragons),
        new Player("Kevin", "Jones", "Center", dragons),
        new Player("Steve", "Clark", "Shooting Guard", dragons),
        new Player("Brian", "Moore", "Point Guard", dragons),
        new Player("Jason", "Hall", "Small Forward", dragons),
        new Player("Thomas", "Young", "Power Forward", dragons),
        new Player("Robert", "King", "Center", dragons),

        // Titans
        new Player("Alex", "Brown", "Point Guard", titans),
        new Player("David", "Williams", "Shooting Guard", titans),
        new Player("Eric", "Johnson", "Small Forward", titans),
        new Player("Andrew", "Taylor", "Power Forward", titans),
        new Player("Anthony", "Green", "Center", titans),
        new Player("Peter", "Adams", "Shooting Guard", titans),
        new Player("James", "White", "Point Guard", titans),
        new Player("Patrick", "Robinson", "Small Forward", titans),
        new Player("Kyle", "Martin", "Power Forward", titans),
        new Player("Ethan", "Scott", "Center", titans),

        // Falcons
        new Player("Henry", "Carter", "Point Guard", falcons),
        new Player("Ryan", "Lewis", "Shooting Guard", falcons),
        new Player("Samuel", "Clark", "Small Forward", falcons),
        new Player("Charles", "Miller", "Power Forward", falcons),
        new Player("Brandon", "Wilson", "Center", falcons),
        new Player("Edward", "Turner", "Shooting Guard", falcons),
        new Player("Adam", "Harris", "Point Guard", falcons),
        new Player("Justin", "Parker", "Small Forward", falcons),
        new Player("George", "Thompson", "Power Forward", falcons),
        new Player("Oliver", "Brooks", "Center", falcons),

        // Thunderhawks
        new Player("Tyler", "Evans", "Point Guard", thunderhawks),
        new Player("Joshua", "Ward", "Shooting Guard", thunderhawks),
        new Player("Nicholas", "Cook", "Small Forward", thunderhawks),
        new Player("Matthew", "Morgan", "Power Forward", thunderhawks),
        new Player("Benjamin", "Hill", "Center", thunderhawks),
        new Player("Elijah", "Murphy", "Shooting Guard", thunderhawks),
        new Player("Christopher", "Phillips", "Point Guard", thunderhawks),
        new Player("Caleb", "Rogers", "Small Forward", thunderhawks),
        new Player("Nathan", "Cooper", "Power Forward", thunderhawks),
        new Player("Logan", "Bailey", "Center", thunderhawks),

        // Silverbacks
        new Player("Aaron", "Reed", "Point Guard", silverbacks),
        new Player("Austin", "Powell", "Shooting Guard", silverbacks),
        new Player("Jacob", "Collins", "Small Forward", silverbacks),
        new Player("Zachary", "Stewart", "Power Forward", silverbacks),
        new Player("Alexander", "Ross", "Center", silverbacks),
        new Player("Jack", "Carter", "Shooting Guard", silverbacks),
        new Player("Dylan", "Bell", "Point Guard", silverbacks),
        new Player("Lucas", "Fisher", "Small Forward", silverbacks),
        new Player("Elijah", "Ward", "Power Forward", silverbacks),
        new Player("Gabriel", "Reed", "Center", silverbacks),

        // Raptors
        new Player("Max", "Bennett", "Point Guard", raptors),
        new Player("Jordan", "Sanders", "Shooting Guard", raptors),
        new Player("Gavin", "Hughes", "Small Forward", raptors),
        new Player("Owen", "Kelly", "Power Forward", raptors),
        new Player("Sebastian", "Flores", "Center", raptors),
        new Player("Caleb", "Diaz", "Shooting Guard", raptors),
        new Player("Tyler", "Long", "Point Guard", raptors),
        new Player("Levi", "Myers", "Small Forward", raptors),
        new Player("Hunter", "Bryant", "Power Forward", raptors),
        new Player("Carter", "Anderson", "Center", raptors),

        // Cyclones
        new Player("Wyatt", "Foster", "Point Guard", cyclones),
        new Player("Isaac", "Powell", "Shooting Guard", cyclones),
        new Player("Luke", "Russell", "Small Forward", cyclones),
        new Player("Jayden", "Simmons", "Power Forward", cyclones),
        new Player("Lucas", "Torres", "Center", cyclones),
        new Player("Mason", "Richardson", "Shooting Guard", cyclones),
        new Player("Logan", "Fisher", "Point Guard", cyclones),
        new Player("Dominic", "Hayes", "Small Forward", cyclones),
        new Player("Oliver", "Peterson", "Power Forward", cyclones),
        new Player("Cameron", "Morgan", "Center", cyclones),

        // Mustangs
        new Player("Ethan", "Perry", "Point Guard", mustangs),
        new Player("James", "Jenkins", "Shooting Guard", mustangs),
        new Player("Connor", "Gibson", "Small Forward", mustangs),
        new Player("Caleb", "Gray", "Power Forward", mustangs),
        new Player("Jonathan", "Barnes", "Center", mustangs),
        new Player("Evan", "Allen", "Shooting Guard", mustangs),
        new Player("Daniel", "Murphy", "Point Guard", mustangs),
        new Player("Samuel", "Diaz", "Small Forward", mustangs),
        new Player("Michael", "Price", "Power Forward", mustangs),
        new Player("Ryan", "Ward", "Center", mustangs),

        // Comets
        new Player("Noah", "Jenkins", "Point Guard", comets),
        new Player("Hunter", "Coleman", "Shooting Guard", comets),
        new Player("Dylan", "Bryant", "Small Forward", comets),
        new Player("Logan", "Reed", "Power Forward", comets),
        new Player("Mason", "Patterson", "Center", comets),
        new Player("Aiden", "Scott", "Shooting Guard", comets),
        new Player("Jackson", "Brooks", "Point Guard", comets),
        new Player("Carter", "Sanders", "Small Forward", comets),
        new Player("Christian", "Bell", "Power Forward", comets),
        new Player("Isaac", "Cook", "Center", comets),

        // Phoenix
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
        ));
    };
  }
}
