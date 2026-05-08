package com.neonark.neonarkcapstone.entity;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "feeding_schedules")
public class FeedingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime feedingTime;
    private String foodType;

    @ManyToOne
    @JoinColumn(name = "creature_id")
    private Creature creature;

    public FeedingSchedule() {}

    public FeedingSchedule(LocalTime feedingTime, String foodType, Creature creature) {
        this.feedingTime = feedingTime;
        this.foodType = foodType;
        this.creature = creature;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getFeedingTime() {
        return feedingTime;
    }

    public String getFoodType() {
        return foodType;
    }

    public Creature getCreature() {
        return creature;
    }

    public void setFeedingTime(LocalTime feedingTime) {
        this.feedingTime = feedingTime;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }
}