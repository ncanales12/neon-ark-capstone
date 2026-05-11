package com.neonark.neonarkcapstone.dto;

public class RenameCreatureResponse {

    private Long id;
    private String oldName;
    private String newName;
    private String habitatName;
    private String status;

    public RenameCreatureResponse(Long id, String oldName, String newName, String habitatName, String status) {
        this.id = id;
        this.oldName = oldName;
        this.newName = newName;
        this.habitatName = habitatName;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getOldName() {
        return oldName;
    }

    public String getNewName() {
        return newName;
    }

    public String getHabitatName() {
        return habitatName;
    }

    public String getStatus() {
        return status;
    }
}