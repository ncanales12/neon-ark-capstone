package com.neonark.neonarkcapstone.repository;

import com.neonark.neonarkcapstone.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation, Long> {

    // get all observations for a creature
    List<Observation> findByCreatureId(Long creatureId);
}