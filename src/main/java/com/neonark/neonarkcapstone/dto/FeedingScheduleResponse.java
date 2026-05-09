package com.neonark.neonarkcapstone.dto;

import java.time.LocalTime;

public class FeedingScheduleResponse {

    private Long id;
    private LocalTime feedingTime;
    private String foodType;
    private Long creatureId;
    private String creatureName;

    public FeedingScheduleResponse(Long id, LocalTime feedingTime, String foodType, Long creatureId, String creatureName) {
        this.id = id;
        this.feedingTime = feedingTime;
        this.foodType = foodType;
        this.creatureId = creatureId;
        this.creatureName = creatureName;
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

    public Long getCreatureId() {
        return creatureId;
    }

    public String getCreatureName() {
        return creatureName;
    }
}
