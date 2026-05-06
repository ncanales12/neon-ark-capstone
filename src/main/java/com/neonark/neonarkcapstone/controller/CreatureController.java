package com.neonark.neonarkcapstone.controller;

import com.neonark.neonarkcapstone.entity.Creature;
import com.neonark.neonarkcapstone.repository.CreatureRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creatures")
public class CreatureController {

    private final CreatureRepository creatureRepository;

    public CreatureController(CreatureRepository creatureRepository) {
        this.creatureRepository = creatureRepository;
    }

    @GetMapping
    public List<Creature> getCreatures() {
        return creatureRepository.findAll();
    }

    @GetMapping("/{id}")
    public Creature getCreatureById(@PathVariable Long id) {
        return creatureRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Creature createCreature(@RequestBody Creature creature) {
        return creatureRepository.save(creature);
    }

    @PutMapping("/{id}/name")
    public Creature renameCreature(@PathVariable Long id, @RequestBody Creature updatedCreature) {
        Creature existing = creatureRepository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        // updates only the name because the capstone has a separate rename route
        existing.setName(updatedCreature.getName());

        return creatureRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteCreature(@PathVariable Long id) {
        creatureRepository.deleteById(id);
    }
}