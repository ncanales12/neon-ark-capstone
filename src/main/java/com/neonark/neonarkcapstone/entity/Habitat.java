package com.neonark.neonarkcapstone.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "habitats")
public class Habitat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String climateType;

    public Habitat() {}

    public Habitat(String name, String climateType) {
        this.name = name;
        this.climateType = climateType;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClimateType() {
        return climateType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClimateType(String climateType) {
        this.climateType = climateType;
    }
}