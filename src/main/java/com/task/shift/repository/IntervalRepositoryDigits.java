package com.task.shift.repository;

import com.task.shift.model.IntervalDigits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervalRepositoryDigits extends JpaRepository<IntervalDigits, Long> {
    @Query(value = "SELECT * " +
            "FROM table_interval_digits " +
            "ORDER BY column_start, column_end " +
            "LIMIT 1;", nativeQuery = true)
    List<String> findMinDigits();
}