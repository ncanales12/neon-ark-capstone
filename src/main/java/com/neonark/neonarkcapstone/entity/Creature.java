package com.neonark.neonarkcapstone.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "creatures")
public class Creature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // basic constructor
    public Creature() {}

    public Creature(String name) {
        this.name = name;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}