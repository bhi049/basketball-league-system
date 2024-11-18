package com.basketball.league.dto;

public class TeamDTO {

  private Long id;
  private String name;
  private String coach;
  private String city;

  public TeamDTO() {}

  public TeamDTO(Long id, String name, String coach, String city) {
    this.id = id;
    this.name = name;
    this.coach = coach;
    this.city = city;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

}
