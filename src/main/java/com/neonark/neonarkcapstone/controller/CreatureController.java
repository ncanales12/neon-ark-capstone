package com.neonark.neonarkcapstone.controller;

import com.neonark.neonarkcapstone.dto.CreatureResponse;
import com.neonark.neonarkcapstone.entity.Creature;
import com.neonark.neonarkcapstone.entity.FeedingSchedule;
import com.neonark.neonarkcapstone.entity.Observation;
import com.neonark.neonarkcapstone.repository.CreatureRepository;
import com.neonark.neonarkcapstone.repository.FeedingScheduleRepository;
import com.neonark.neonarkcapstone.repository.ObservationRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/creatures")
public class CreatureController {

    private final CreatureRepository creatureRepository;
    private final ObservationRepository observationRepository;
    private final FeedingScheduleRepository feedingScheduleRepository;

    public CreatureController(CreatureRepository creatureRepository,
                              ObservationRepository observationRepository,
                              FeedingScheduleRepository feedingScheduleRepository) {
        this.creatureRepository = creatureRepository;
        this.observationRepository = observationRepository;
        this.feedingScheduleRepository = feedingScheduleRepository;
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

    @GetMapping("/{id}/observations")
    public List<Observation> getCreatureObservations(@PathVariable Long id) {
        return observationRepository.findByCreatureId(id);
    }

    @PostMapping("/{id}/observations")
    public Observation createCreatureObservation(@PathVariable Long id, @RequestBody Observation observation) {
        Creature creature = creatureRepository.findById(id).orElse(null);

        if (creature == null) {
            return null;
        }

        observation.setCreature(creature);
        observation.setCreatedAt(LocalDateTime.now());

        return observationRepository.save(observation);
    }

    @PostMapping("/{id}/feedings")
    public FeedingSchedule createCreatureFeeding(@PathVariable Long id, @RequestBody FeedingSchedule feedingSchedule) {
        Creature creature = creatureRepository.findById(id).orElse(null);

        if (creature == null) {
            return null;
        }

        feedingSchedule.setCreature(creature);

        return feedingScheduleRepository.save(feedingSchedule);
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