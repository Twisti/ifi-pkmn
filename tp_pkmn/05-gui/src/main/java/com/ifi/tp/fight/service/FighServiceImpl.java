package com.ifi.tp.fight.service;

import com.ifi.tp.fight.bo.Fight;
import com.ifi.tp.repository.FightRepository;
import com.ifi.tp.trainers.bo.Pokemon;
import com.ifi.tp.trainers.bo.Trainer;
import com.ifi.tp.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FighServiceImpl implements FightService {

    private  RestTemplate restTemplate;

    @Autowired
    private FightRepository fightRepository;

    @Autowired
    private TrainerService trainerService;


    @Autowired
    public FighServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }



    @Override
    public Iterable<Fight>  getAllFights() {
        return this.fightRepository.findAll();
    }

    @Override
    public Iterable<Fight> getAllFightsFromTrainer(int idTrainer) {
        return null;
    }

    @Override
    public Fight getFight(int idFight) {
        return this.fightRepository.findById(idFight).orElse(null);
    }

    @Override
    public Fight createFight(Fight f) {
        return this.fightRepository.save(f);
    }

    @Override
    public Fight executeFight(Fight fight) {
        Trainer tA= trainerService.getTrainer(fight.trainerA());
        Trainer tB = trainerService.getTrainer(fight.trainerB());
        if (tA == null || tB == null) {return  fight;}
        else {

            fight.getCombatStep().add("Début du combat");


            while(tA.getTeam().size() != 0 &&  tB.getTeam().size() != 0) {

                Pokemon pkmA = tA.getTeam().get(0);
                Pokemon pkmB = tB.getTeam().get(0);


                pkmB.setHp(pkmB.getLevel() + pkmB.getType().getStats().getHp());
                pkmA.setHp(pkmA.getLevel() + pkmA.getType().getStats().getHp());
                boolean pkmAStrike = false;

                if (pkmA.getType().getStats().getSpeed() + pkmA.getLevel() > pkmB.getType().getStats().getSpeed() + pkmB.getLevel()) {
                    pkmAStrike = true;
                    fight.getCombatStep().add(pkmA.getType().getName() + " commence le combat.");
                } else {
                    fight.getCombatStep().add(pkmB.getType().getName() + " commence le combat.");

                }


                int tmpDmg;
                while (pkmA.getHp() > 0 && pkmB.getHp() > 0) {
                    if (pkmAStrike) {
                        tmpDmg = calculDamage(pkmA, pkmB);
                        pkmB.setHp(pkmB.getHp() - tmpDmg);
                        if (pkmB.getHp() < 0) pkmB.setHp(0);
                        fight.getCombatStep().add(pkmA.getType().getName() + "(" + pkmA.getHp() + "HP)" + " inflige " + tmpDmg + " à " + pkmB.getType().getName() + "(" + pkmB.getHp() + "HP)");
                        pkmAStrike = false;

                    } else {

                        tmpDmg = calculDamage(pkmB, pkmA);
                        pkmA.setHp(pkmA.getHp() - tmpDmg);
                        if (pkmA.getHp() < 0) pkmA.setHp(0);
                        fight.getCombatStep().add(pkmB.getType().getName() + "(" + pkmB.getHp() + "HP)" + " inflige " + tmpDmg + " à " + pkmA.getType().getName() + "(" + pkmA.getHp() + "HP)");
                        pkmAStrike = true;

                    }
                }


                if (pkmA.getHp() == 0) {
                    fight.getCombatStep().add(pkmA.getType().getName() + " est KO...");
                    tA.getTeam().remove(pkmA);

                } else {
                    fight.getCombatStep().add(pkmB.getType().getName() + " est KO...");
                    tB.getTeam().remove(pkmB);
                }

                if(tA.getTeam().size() == 0 ){
                    fight.getCombatStep().add(tA.getName() + "n'est plus de pokémons en vie...");
                    fight.setWinner(tB.getName());
                    fight.getCombatStep().add(tB.getName() + " est gagnant.");
                }else if (tB.getTeam().size() == 0){
                    fight.getCombatStep().add(tB.getName() + "  n'a plus de pokémons en vie...");
                    fight.setWinner(tA.getName());
                    fight.getCombatStep().add(tA.getName() + " est gagnant.");;
                }

            }

            return fight;

        }
    }


    private int calculDamage(Pokemon attack, Pokemon def){
        int attck = attack.getType().getStats().getAttack() + attack.getLevel();
        int defence = def.getType().getStats().getDefense() + def.getLevel();


        int result = attck - defence;
        if (result <=0) { return  1;}
        return  result;

    }




}
