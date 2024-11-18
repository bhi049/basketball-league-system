package com.basketball.league.dto;

public class StandingsDTO {
  private String teamName;
  private int wins;
  private int losses;
  private int scored;
  private int conceded;

  // Constructor
  public StandingsDTO(String teamName, int wins, int losses, int scored, int conceded) {
    this.teamName = teamName;
    this.wins = wins;
    this.losses = losses;
    this.scored = scored;
    this.conceded = conceded;
  }

  // Getters and Setters
  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }

  public int getWins() {
    return wins;
  }

  public void setWins(int wins) {
    this.wins = wins;
  }

  public int getLosses() {
    return losses;
  }

  public void setLosses(int losses) {
    this.losses = losses;
  }

  public int getScored() {
    return scored;
  }

  public void setScored(int scored) {
    this.scored = scored;
  }

  public int getConceded() {
    return conceded;
  }

  public void setConceded(int conceded) {
    this.conceded = conceded;
  }

  // Calculate score differential
  public int getDifferential() {
    return scored - conceded;
  }
}
