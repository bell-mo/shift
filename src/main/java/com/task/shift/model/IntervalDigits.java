package com.task.shift.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "table_interval_digits")
public class IntervalDigits {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "column_start")
    private int start;
    @Column(name = "column_end")
    private int end;

    public IntervalDigits() { }

    public IntervalDigits(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public List<Integer> toList() {
        List<Integer> res = new ArrayList<>();
        res.add(this.getStart());
        res.add(this.getEnd());
        return res;
    }
}