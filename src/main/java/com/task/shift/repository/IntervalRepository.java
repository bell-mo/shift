package com.task.shift.repository;

import com.task.shift.model.Interval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervalRepository extends JpaRepository<Interval, Long> {
    @Query(value = "SELECT * FROM table_interval ORDER BY column_start LIMIT 1;", nativeQuery = true)
    List<Integer> findMin();
}