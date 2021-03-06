package com.ifi.tp.trainers.bo;

import java.util.List;

public class Trainer {

    private String name;

    private List<Pokemon> team;

    private String picture;

    private int pokedollars = 200;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    public void setTeam(List<Pokemon> team) {
        this.team = team;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getPokedollars() { return pokedollars;  }

    public void setPokedollars(int pokedollars) { this.pokedollars = pokedollars; }
}
