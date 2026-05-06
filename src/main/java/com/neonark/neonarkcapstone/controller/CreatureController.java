package com.neonark.neonarkcapstone.controller;

import com.neonark.neonarkcapstone.entity.Creature;
import com.neonark.neonarkcapstone.repository.CreatureRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreatureController {

    private final CreatureRepository creatureRepository;

    public CreatureController(CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }

    @GetMapping("/creatures")
    public List<Creature> getCreatures() {
        return creatureRepository.findAll();
    }
}