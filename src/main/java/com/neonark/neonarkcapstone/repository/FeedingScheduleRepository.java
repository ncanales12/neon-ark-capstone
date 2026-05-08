package com.neonark.neonarkcapstone.repository;

import com.neonark.neonarkcapstone.entity.FeedingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface FeedingScheduleRepository extends JpaRepository<FeedingSchedule, Long> {

    // find feedings at a specific time
    List<FeedingSchedule> findByFeedingTime(LocalTime feedingTime);
}