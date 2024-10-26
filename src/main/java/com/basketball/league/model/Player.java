package com.basketball.league.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long playerid;

  private String firstname, lastname, position;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "teamid")
  private Team team;


  public Player() {}


  public Player(String firstname, String lastname, String position, Team team) {
    super();
    this.firstname = firstname;
    this.lastname = lastname;
    this.position = position;
    this.team = team;
  }

  public Long getPlayerid() {
    return playerid;
  }


  public void setPlayerid(Long playerid) {
    this.playerid = playerid;
  }


  public String getFirstname() {
    return firstname;
  }


  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }


  public String getLastname() {
    return lastname;
  }


  public void setLastname(String lastname) {
    this.lastname = lastname;
  }


  public String getPosition() {
    return position;
  }


  public void setPosition(String position) {
    this.position = position;
  }


  public Team getTeam() {
    return team;
  }


  public void setTeam(Team team) {
    this.team = team;
  }
}
