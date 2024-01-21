package com.task.shift.model;
import jakarta.persistence.*;

@Entity
@Table(name = "table_interval")
public class Interval {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "column_start")
    private int start;
    @Column(name = "column_end")
    private int end;

    public Interval() { }

    public Interval(int start, int end) {
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
}