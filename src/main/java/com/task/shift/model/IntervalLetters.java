package com.task.shift.model;
import jakarta.persistence.*;

@Entity
@Table(name = "table_interval_letters")
public class IntervalLetters {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "column_start")
    private char start;
    @Column(name = "column_end")
    private char end;

    public IntervalLetters() { }

    public IntervalLetters(char start, char end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public char getEnd() {
        return end;
    }

    public void setEnd(char end) {
        this.end = end;
    }

    public void setStart(char start) {
        this.start = start;
    }
}