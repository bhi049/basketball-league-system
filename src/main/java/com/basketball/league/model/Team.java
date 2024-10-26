package com.basketball.league.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Team {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long teamid;

  private String name, coach, city;

  @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
  private List<Player> players;

  public Team() {}
  
  public Team(String name, String coach, String city) {
    super();
    this.name = name;
    this.coach = coach;
    this.city = city;
  }

  public Long getTeamid() {
    return teamid;
  }

  public void setTeamid(Long teamid) {
    this.teamid = teamid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCoach() {
    return coach;
  }

  public void setCoach(String coach) {
    this.coach = coach;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }
}

