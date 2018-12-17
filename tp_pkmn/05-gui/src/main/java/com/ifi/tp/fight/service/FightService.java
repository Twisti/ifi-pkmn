package com.ifi.tp.fight.service;

import com.ifi.tp.fight.bo.Fight;
import com.ifi.tp.trainers.bo.Trainer;

public interface FightService {
    Iterable<Fight>   getAllFights();
    Iterable<Fight> getAllFightsFromTrainer(Trainer t) ;

    Fight getFight(int idFight);

    Fight createFight(Fight fight);

    Fight executeFight(Fight fight);

}
