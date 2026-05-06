package com.neonark.neonarkcapstone.repository;

import com.neonark.neonarkcapstone.entity.Creature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatureRepository extends JpaRepository<Creature, Long> {
}