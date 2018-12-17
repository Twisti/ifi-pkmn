package com.ifi.tp.fight.service;

import com.ifi.tp.fight.bo.Fight;

public interface FightService {
    Iterable<Fight>   getAllFights();
    Iterable<Fight>   getAllFightsFromTrainer(int idTrainer);
    Fight getFight(int idFight);

    Fight createFight(Fight fight);

    Fight executeFight(Fight fight);

}
