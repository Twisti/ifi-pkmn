package com.ifi.tp.fight.bo;

import com.ifi.tp.trainers.bo.Trainer;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Fight {

    @Id
    @GeneratedValue
    private int id;
    private String trainerA,trainerB;

    @Cascade({CascadeType.ALL})
    @Column(name="logs")
    @OneToMany(cascade = javax.persistence.CascadeType.ALL)
    private List<FightLog> combatStep = new ArrayList<>();

    private String winner ="no";


    public String trainerA() {
        return trainerA;
    }
    public String trainerB() {
        return trainerB;
    }

    public List<FightLog> getCombatStep() {
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

    public int getId() {
        return id;
    }
}
