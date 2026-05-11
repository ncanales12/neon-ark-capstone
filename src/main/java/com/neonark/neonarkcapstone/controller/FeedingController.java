package com.neonark.neonarkcapstone.controller;

import com.neonark.neonarkcapstone.dto.FeedingScheduleResponse;
import com.neonark.neonarkcapstone.entity.FeedingSchedule;
import com.neonark.neonarkcapstone.repository.FeedingScheduleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/feedings")
public class FeedingController {

    private final FeedingScheduleRepository feedingScheduleRepository;

    public FeedingController(FeedingScheduleRepository feedingScheduleRepository) {
        this.feedingScheduleRepository = feedingScheduleRepository;
    }

    @GetMapping
    public ResponseEntity<List<FeedingScheduleResponse>> getFeedingsByTime(@RequestParam String time) {

        LocalTime feedingTime;

        try {
            feedingTime = LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }

        List<FeedingScheduleResponse> responses = feedingScheduleRepository.findByFeedingTime(feedingTime)
                .stream()
                .map(this::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    private FeedingScheduleResponse toResponse(FeedingSchedule feedingSchedule) {
        Long creatureId = null;
        String creatureName = null;

        if (feedingSchedule.getCreature() != null) {
            creatureId = feedingSchedule.getCreature().getId();
            creatureName = feedingSchedule.getCreature().getName();
        }

        return new FeedingScheduleResponse(
                feedingSchedule.getId(),
                feedingSchedule.getFeedingTime(),
                feedingSchedule.getFoodType(),
                creatureId,
                creatureName
        );
    }
}