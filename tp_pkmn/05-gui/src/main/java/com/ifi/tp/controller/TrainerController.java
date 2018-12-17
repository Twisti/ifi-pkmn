package com.ifi.tp.controller;

import com.ifi.tp.trainers.bo.Trainer;
import com.ifi.tp.trainers.service.TrainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @GetMapping("/trainers")
    ModelAndView index(){
        var modelAndView = new ModelAndView("trainers");

        modelAndView.addObject("trainers", trainerService.getAllTrainers());

        return modelAndView;
    }

    @GetMapping("/trainers/{name}")
    ModelAndView index(@PathVariable String name){
        var modelAndView = new ModelAndView("trainer");

        modelAndView.addObject("trainer", trainerService.getTrainer(name));

        return modelAndView;
    }


    @GetMapping("/trainers/{name}/fights")
    ModelAndView getFights(@PathVariable String name){
        var modelAndView = new ModelAndView("trainer");

        modelAndView.addObject("trainer", trainerService.getTrainer(name));

        return modelAndView;
    }

    @GetMapping("/trainers/{trainer}/arena")
    ModelAndView lookingForFight(@PathVariable String trainer){
        var modelAndView = new ModelAndView("arena");

        List<Trainer> trainers = trainerService.getAllTrainers();
        List<Trainer> otherTrainers = new ArrayList<>();

        for (Trainer t: trainers) {
            if(!t.getName().equals(trainer)){
                otherTrainers.add(t);
            }

        }
        modelAndView.addObject("trainers", otherTrainers);

        return  modelAndView;
    }
}
