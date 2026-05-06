package com.neonark.neonarkcapstone.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "creatures")
public class Creature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "habitat_id")
    private Habitat habitat;

    public Creature() {}

    public Creature(String name, Habitat habitat) {
        this.name = name;
        this.habitat = habitat;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }
}