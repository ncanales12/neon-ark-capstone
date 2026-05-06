package com.neonark.neonarkcapstone.controller;

import com.neonark.neonarkcapstone.entity.Creature;
import com.neonark.neonarkcapstone.repository.CreatureRepository;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/creatures/{id}")
    public Creature getCreatureById(@PathVariable Long id) {
        return creatureRepository.findById(id).orElse(null);
    }

    @PostMapping("/creatures")
    public Creature createCreature(@RequestBody Creature creature) {
        return creatureRepository.save(creature);
    }

    @DeleteMapping("/creatures/{id}")
    public void deleteCreature(@PathVariable Long id) {
        creatureRepository.deleteById(id);
    }
}