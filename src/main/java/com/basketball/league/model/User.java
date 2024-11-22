package com.basketball.league.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String role;

  @ManyToOne
  private Team favouriteTeam;

  @ManyToOne
  private Player favouritePlayer;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Team getFavouriteTeam() {
    return favouriteTeam;
  }

  public void setFavouriteTeam(Team favouriteTeam) {
    this.favouriteTeam = favouriteTeam;
  }

  public Player getFavouritePlayer() {
    return favouritePlayer;
  }

  public void setFavouritPlayer(Player favouritePlayer) {
    this.favouritePlayer = favouritePlayer;
  }

}
