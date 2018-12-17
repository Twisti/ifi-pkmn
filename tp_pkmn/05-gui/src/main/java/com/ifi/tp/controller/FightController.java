package com.ifi.tp.controller;


import com.ifi.tp.fight.bo.Fight;
import com.ifi.tp.fight.service.FightService;

import com.ifi.tp.trainers.bo.Trainer;
import com.ifi.tp.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class FightController {

    @Autowired
    private FightService fightService;
    @Autowired
    private TrainerService trainerService;


    @Autowired
    FightController(FightService fightService) {
        this.fightService = fightService;
    }


    @GetMapping("/fights")
    ModelAndView index(){
        var modelAndView = new ModelAndView("fights");

        ArrayList<Fight> fights = new ArrayList<>();
        Fight a = new Fight();
        a.setTrainerB("Ash");
        a.setTrainerA("Misty");
       // fightService.executeFight(a);
        //fightService.createFight(a);
        modelAndView.addObject("fights",fightService.getAllFights());

        return modelAndView;
    }



    @GetMapping("/fight/{id}")
    ModelAndView index(@PathVariable String id){
        var modelAndView = new ModelAndView("fight");

        modelAndView.addObject("fight", fightService.getFight(Integer.parseInt(id)));

        return modelAndView;
    }

    @GetMapping("/fights/{name}")
    ModelAndView getFromTrainer(@PathVariable String name){
        var modelAndView = new ModelAndView("fights");

        modelAndView.addObject("fights", fightService.getAllFightsFromTrainer(trainerService.getTrainer(name)));
        return modelAndView;
    }


    @PostMapping("/fights/{trainer1}/{trainer2}")
    String postFight(@PathVariable String trainer1, @PathVariable String trainer2) {
        Trainer t1 = trainerService.getTrainer(trainer1);
        Trainer t2 = trainerService.getTrainer(trainer2);
        ArrayList<Fight> fights = new ArrayList<>();
        Fight a = new Fight();
        a.setTrainerB(trainer1);
        a.setTrainerA(trainer2);
        fightService.executeFight(a);
        fightService.createFight(a);
        return "redirect:/fight/" + a.getId();
    }

}
