package com.neonark.neonarkcapstone.dto;

public class CreatureResponse {

    private Long id;
    private String name;
    private String habitatName;

    public CreatureResponse(Long id, String name, String habitatName) {
        this.id = id;
        this.name = name;
        this.habitatName = habitatName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHabitatName() {
        return habitatName;
    }
}