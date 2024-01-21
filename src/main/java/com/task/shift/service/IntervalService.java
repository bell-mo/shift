package com.task.shift.service;

import com.task.shift.model.IntervalDigits;
import com.task.shift.model.IntervalLetters;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@org.springframework.stereotype.Service
public class IntervalService {

    public static List<List<Integer>> getListDigits(List<List<String>> intervals) {
        List<List<Integer>> intervalsDigits = new ArrayList<>();

        for (int i = 0; i < intervals.size(); i++) {
            List<String> interval = intervals.get(i);
            List<Integer> intervalDigits = new ArrayList<>();
            intervalDigits.add(Integer.parseInt(interval.get(0)));
            intervalDigits.add(Integer.parseInt(interval.get(1)));
            intervalsDigits.add(intervalDigits);
        }
        intervalsDigits.sort(Comparator.comparingInt(List::getFirst));
        return intervalsDigits;
    }

    public static List<List<Character>> getListLetters(List<List<String>> intervals) {
        List<List<Character>> intervalsLetters = new ArrayList<>();

        for (int i = 0; i < intervals.size(); i++) {
            List<String> interval = intervals.get(i);
            List<Character> intervalLetters = new ArrayList<>();
            intervalLetters.add(interval.get(0).charAt(0));
            intervalLetters.add(interval.get(1).charAt(0));
            intervalsLetters.add(intervalLetters);
        }
        return intervalsLetters;
    }
    public static List<IntervalDigits> getMergedListDigits(List<List<Integer>> intervals) {
        List<IntervalDigits> mergedList = new ArrayList<>();
        IntervalDigits prev = new IntervalDigits(intervals.get(0).get(0), intervals.get(0).get(1));

        for (int i = 1; i < intervals.size(); i++) {
            IntervalDigits current = new IntervalDigits(intervals.get(i).get(0), intervals.get(i).get(1));
            if (current.getStart() <= prev.getEnd()) {
                // Слияние интервалов
                prev.setEnd(Math.max(prev.getEnd(), current.getEnd()));
            } else {
                // Добавление слияния в результат и обновление предыдущего интервала
                mergedList.add(prev);
                prev = current;
            }
        }
        mergedList.add(prev);
        return mergedList;
    }


    public static List<IntervalLetters> getMergedListLetters(List<List<Character>> intervals) {
        List<IntervalLetters> mergedList = new ArrayList<>();
        IntervalLetters prev = new IntervalLetters(intervals.get(0).get(0), intervals.get(0).get(1));

        for (int i = 1; i < intervals.size(); i++) {
            IntervalLetters current = new IntervalLetters(intervals.get(i).get(0), intervals.get(i).get(1));
            if (current.getStart() <= prev.getEnd()) {
                // Слияние интервалов
                if (current.getEnd() >= prev.getEnd())
                    prev.setEnd(current.getEnd());
                else prev.setEnd(prev.getEnd());
            } else {
                // Добавление слияния в результат и обновление предыдущего интервала
                mergedList.add(prev);
                prev = current;
            }
        }
        mergedList.add(prev);
        return mergedList;
    }
}
