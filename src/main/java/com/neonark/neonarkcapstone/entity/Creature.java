package com.neonark.neonarkcapstone.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "creatures")
public class Creature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String status = "ACTIVE";

    @ManyToOne
    @JoinColumn(name = "habitat_id")
    private Habitat habitat;

    public Creature() {}

    public Creature(String name, Habitat habitat) {
        this.name = name;
        this.habitat = habitat;
        this.status = "ACTIVE";
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }
}