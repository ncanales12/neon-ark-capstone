package com.neonark.neonarkcapstone.controller;

import com.neonark.neonarkcapstone.entity.FeedingSchedule;
import com.neonark.neonarkcapstone.repository.FeedingScheduleRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/feedings")
public class FeedingController {

    private final FeedingScheduleRepository feedingScheduleRepository;

    public FeedingController(FeedingScheduleRepository feedingScheduleRepository) {
        this.feedingScheduleRepository = feedingScheduleRepository;
    }

    @GetMapping
    public List<FeedingSchedule> getFeedingsByTime(@RequestParam String time) {
        LocalTime feedingTime = LocalTime.parse(time);

        return feedingScheduleRepository.findByFeedingTime(feedingTime);
    }
}