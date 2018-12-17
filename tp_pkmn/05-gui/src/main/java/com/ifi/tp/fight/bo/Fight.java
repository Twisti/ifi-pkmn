package com.ifi.tp.fight.bo;

import com.ifi.tp.trainers.bo.Trainer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Fight {

    @Id
    @GeneratedValue
    private int id;
    private String trainerA,trainerB;

    @Column(name="logs")
    @ElementCollection(targetClass=String.class)
    private List<String> combatStep = new ArrayList<>();

    private String winner ="no";


    public String trainerA() {
        return trainerA;
    }
    public String trainerB() {
        return trainerB;
    }

    public List<String> getCombatStep() {
        return combatStep;
    }

    public void setTrainerA(String trainerA) {
        this.trainerA = trainerA;
    }

    public void setTrainerB(String trainerB) {
        this.trainerB = trainerB;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
