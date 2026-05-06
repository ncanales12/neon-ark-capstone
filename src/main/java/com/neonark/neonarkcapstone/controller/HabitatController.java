package com.neonark.neonarkcapstone.controller;

import com.neonark.neonarkcapstone.entity.Habitat;
import com.neonark.neonarkcapstone.repository.HabitatRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitats")
public class HabitatController {

    private final HabitatRepository habitatRepository;

    public HabitatController(HabitatRepository habitatRepository) {
        this.habitatRepository = habitatRepository;
    }

    @GetMapping
    public List<Habitat> getHabitats() {
        return habitatRepository.findAll();
    }

    @PostMapping
    public Habitat createHabitat(@RequestBody Habitat habitat) {
        return habitatRepository.save(habitat);
    }
}