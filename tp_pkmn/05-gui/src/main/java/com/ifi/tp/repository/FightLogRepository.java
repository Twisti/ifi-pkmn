package com.ifi.tp.repository;

import com.ifi.tp.fight.bo.FightLog;
import org.springframework.data.repository.CrudRepository;
import com.ifi.tp.fight.bo.Fight;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FightLogRepository extends CrudRepository<FightLog, Integer> {



}
