package com.task.shift.repository;

import com.task.shift.model.IntervalLetters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervalRepositoryLetters extends JpaRepository<IntervalLetters, Long> {
    @Query(value = "SELECT *" +
            "FROM table_interval_letters " +
            "ORDER BY column_start, column_end " +
            "LIMIT 1;", nativeQuery = true)
    IntervalLetters findMin();
}
