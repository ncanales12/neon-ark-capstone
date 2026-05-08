package com.neonark.neonarkcapstone.dto;

import java.time.LocalDateTime;

public class ObservationResponse {

    private Long id;
    private String note;
    private String authorName;
    private Long creatureId;
    private LocalDateTime createdAt;

    public ObservationResponse(Long id, String note, String authorName, Long creatureId, LocalDateTime createdAt) {
        this.id = id;
        this.note = note;
        this.authorName = authorName;
        this.creatureId = creatureId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Long getCreatureId() {
        return creatureId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}