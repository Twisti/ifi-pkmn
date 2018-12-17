package com.ifi.tp.repository;

import org.springframework.data.repository.CrudRepository;
import com.ifi.tp.fight.bo.Fight;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FightRepository extends CrudRepository<Fight, Integer> {

    List<Fight> findByTrainerAOrTrainerB(String t1, String t2);


}
