package com.task.shift.service;
import com.task.shift.model.IntervalDigits;
import com.task.shift.model.IntervalLetters;

import java.util.ArrayList;
import java.util.List;

public class IntervalService {
    public static List<List<Integer>> convertListDigits(List<List<String>> inputList) {
        // Создаем компаратор для сортировки по первому элементу списка
        java.util.Comparator<List<String>> comp = java.util.Comparator.comparing(s -> Integer.parseInt(s.get(0)));

        inputList.sort(comp); // Сортируем входной список по первым элементам

        List<List<Integer>> result = new ArrayList<>();
        for (List<String> subList : inputList) {
            List<Integer> intSubList = new ArrayList<>();
            intSubList.add(Integer.parseInt(subList.get(0)));
            intSubList.add(Integer.parseInt(subList.get(1)));
            result.add(intSubList);
        }
        return result;
    }

    public static List<List<Character>> convertListLetters(List<List<String>> inputList) {
        // Создаем компаратор для сортировки по первому символу каждого списка
        java.util.Comparator<List<String>> comp = java.util.Comparator.comparing(s -> s.get(0).charAt(0));

        inputList.sort(comp); // Сортируем входной список по первым символам

        List<List<Character>> result = new ArrayList<>();
        for (List<String> subList : inputList) {
            List<Character> charSubList = new ArrayList<>();
            charSubList.add(subList.get(0).charAt(0));
            charSubList.add(subList.get(1).charAt(0));
            result.add(charSubList);
        }
        return result;
    }

    public static List<IntervalDigits> getMergedListDigits(List<List<String>> inputList) {

        List<List<Integer>> intervals = convertListDigits(inputList);

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


    public static List<IntervalLetters> getMergedListLetters(List<List<String>> inputList) {

        List<List<Character>> intervals = convertListLetters(inputList);

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
