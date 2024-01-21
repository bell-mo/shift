package com.task.shift.controller;

//import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.task.shift.model.Interval;
import com.task.shift.repository.IntervalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;


//@Service
@RestController
@RequestMapping("/api/v1/intervals")
public class Controller {
    @Autowired
    private IntervalRepository intervalRepository;
    @PostMapping("/merge")
    public ResponseEntity<HttpStatus> mergeIntervals(@RequestParam(defaultValue = "digits") String kind,
                                                     @RequestBody List<List<Integer>> intervals){
        if(intervals.isEmpty()){
            return null;
        }
        if(intervals.size()==1){
            return null;
        }

        // Сортируем по первому элементу, на случай если интервалы были не упорядочены
        intervals.sort(Comparator.comparingInt(e -> e.getFirst()));

        List<Interval> mergedList = getMergedList(intervals);
        for(Interval item : mergedList){
            System.out.println(item.getStart());
            System.out.println(item.getEnd());
            intervalRepository.save(item);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private static List<Interval> getMergedList(List<List<Integer>> intervals) {
        List<Interval> mergedList = new ArrayList<>();
        Interval prev = new Interval(intervals.get(0).get(0), intervals.get(0).get(1));

        for (int i = 1; i < intervals.size(); i++) {
            Interval current = new Interval(intervals.get(i).get(0), intervals.get(i).get(1));
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

    @GetMapping("/min")
    @ResponseBody
    public List<Integer> getMinInterval(@RequestParam(defaultValue = "digits") String kind){
        //Interval res = new Interval(1,2);
        return intervalRepository.findMin();
    }


}