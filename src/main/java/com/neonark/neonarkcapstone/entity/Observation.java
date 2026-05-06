package com.neonark.neonarkcapstone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "observations")
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String note;
    private String authorName;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "creature_id")
    private Creature creature;

    public Observation() {}

    public Observation(String note, String authorName, Creature creature) {
        this.note = note;
        this.authorName = authorName;
        this.creature = creature;
        this.createdAt = LocalDateTime.now();
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }
}