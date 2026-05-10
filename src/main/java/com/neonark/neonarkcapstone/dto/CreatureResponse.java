package com.neonark.neonarkcapstone.dto;

public class CreatureResponse {

    private Long id;
    private String name;
    private String habitatName;
    private String status;

    public CreatureResponse(Long id, String name, String habitatName, String status) {
        this.id = id;
        this.name = name;
        this.habitatName = habitatName;
        this.status = status;
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

    public String getStatus() {
        return status;
    }
}