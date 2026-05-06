package com.neonark.neonarkcapstone.controller;

import com.neonark.neonarkcapstone.dto.CreatureResponse;
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
    public List<CreatureResponse> getCreatures() {
        return creatureRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public CreatureResponse getCreatureById(@PathVariable Long id) {
        Creature creature = creatureRepository.findById(id).orElse(null);

        if (creature == null) {
            return null;
        }

        return toResponse(creature);
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

        existing.setName(updatedCreature.getName());

        return creatureRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteCreature(@PathVariable Long id) {
        creatureRepository.deleteById(id);
    }

    private CreatureResponse toResponse(Creature creature) {
        String habitatName = null;

        if (creature.getHabitat() != null) {
            habitatName = creature.getHabitat().getName();
        }

        return new CreatureResponse(
                creature.getId(),
                creature.getName(),
                habitatName
        );
    }
}