package com.neonark.neonarkcapstone.controller;

import com.neonark.neonarkcapstone.dto.CreatureResponse;
import com.neonark.neonarkcapstone.entity.Creature;
import com.neonark.neonarkcapstone.entity.FeedingSchedule;
import com.neonark.neonarkcapstone.entity.Observation;
import com.neonark.neonarkcapstone.repository.CreatureRepository;
import com.neonark.neonarkcapstone.repository.FeedingScheduleRepository;
import com.neonark.neonarkcapstone.repository.ObservationRepository;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CreatureResponse> getCreatureById(@PathVariable Long id) {
        Creature creature = creatureRepository.findById(id).orElse(null);

        if (creature == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(toResponse(creature));
    }

    @GetMapping("/{id}/observations")
    public ResponseEntity<List<Observation>> getCreatureObservations(@PathVariable Long id) {
        if (!creatureRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(observationRepository.findByCreatureId(id));
    }

    @PostMapping("/{id}/observations")
    public ResponseEntity<Observation> createCreatureObservation(@PathVariable Long id,
                                                                 @RequestBody Observation observation) {

        Creature creature = creatureRepository.findById(id).orElse(null);

        if (creature == null) {
            return ResponseEntity.notFound().build();
        }

        if (observation.getNote() == null || observation.getNote().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        observation.setCreature(creature);
        observation.setCreatedAt(LocalDateTime.now());

        return ResponseEntity.status(201).body(observationRepository.save(observation));
    }

    @PostMapping("/{id}/feedings")
    public ResponseEntity<FeedingSchedule> createCreatureFeeding(@PathVariable Long id,
                                                                 @RequestBody FeedingSchedule feedingSchedule) {

        Creature creature = creatureRepository.findById(id).orElse(null);

        if (creature == null) {
            return ResponseEntity.notFound().build();
        }

        if (feedingSchedule.getFeedingTime() == null) {
            return ResponseEntity.badRequest().build();
        }

        feedingSchedule.setCreature(creature);

        return ResponseEntity.status(201).body(feedingScheduleRepository.save(feedingSchedule));
    }

    @PostMapping
    public ResponseEntity<Creature> createCreature(@RequestBody Creature creature) {

        if (creature.getName() == null || creature.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        Creature saved = creatureRepository.save(creature);

        return ResponseEntity.status(201).body(saved);
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<Creature> renameCreature(@PathVariable Long id,
                                                   @RequestBody Creature updatedCreature) {

        Creature existing = creatureRepository.findById(id).orElse(null);

        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        if (updatedCreature.getName() == null || updatedCreature.getName().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        existing.setName(updatedCreature.getName());

        return ResponseEntity.ok(creatureRepository.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreature(@PathVariable Long id) {

        if (!creatureRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        creatureRepository.deleteById(id);

        return ResponseEntity.noContent().build();
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