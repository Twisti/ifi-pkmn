package com.ifi.tp.fight.bo;

import javax.persistence.*;

@Entity
public class FightLog {

    @Id
    @GeneratedValue
    private int id;
    private int numStep;
    private String message;
    @ManyToOne
    @JoinColumn(name = "fight_id")
    private Fight fight;

    public FightLog(int numStep,String message){
        this.numStep = numStep;
        this.message = message;
    }

    public FightLog(){};

    public int getNumStep() {
        return numStep;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNumStep(int numStep) {
        this.numStep = numStep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fight getFight() {
        return fight;
    }

    public void setFight(Fight fight) {
        this.fight = fight;
    }
}
