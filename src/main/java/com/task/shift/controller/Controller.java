package com.task.shift.controller;

//import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.task.shift.model.IntervalDigits;
import com.task.shift.model.IntervalLetters;
import com.task.shift.service.IntervalService;
import com.task.shift.repository.*;


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
import org.jetbrains.annotations.NotNull;




@RestController
@RequestMapping("/api/v1/intervals")
public class Controller {
    @Autowired
    private IntervalRepositoryDigits intervalRepositoryDigits;
    private IntervalRepositoryLetters intervalRepositoryLetters;
    @PostMapping("/merge")
    public ResponseEntity<HttpStatus> mergeIntervals(@RequestParam(defaultValue = "digits") String kind,
                                                     @NotNull @RequestBody List<List<String>> intervals){
        if(intervals.isEmpty()){
            return null;
        }
        if(intervals.size()==1){
            return null;
        }

        if (Objects.equals(kind, "digits")) {

            List<IntervalDigits> mergedList = IntervalService.getMergedListDigits(IntervalService.getListDigits(intervals));
            for (IntervalDigits item : mergedList) {
                System.out.println(item.getStart());
                System.out.println(item.getEnd());
                intervalRepositoryDigits.save(item);
            }
        } else if (Objects.equals(kind, "letters")) {

            List<IntervalLetters> mergedList = IntervalService.getMergedListLetters(IntervalService.getListLetters(intervals));
            for(IntervalLetters item : mergedList) {
                System.out.println(item.getStart());
                System.out.println(item.getEnd());
                intervalRepositoryLetters.save(item);
            }
        }
        else return (ResponseEntity<HttpStatus>) ResponseEntity.badRequest();
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping("/min")
    @ResponseBody
    public List<String> getMinInterval(@RequestParam(defaultValue = "digits") String kind){
        if (kind == "digits") return intervalRepositoryDigits.findMinDigits();
        else if (kind == "letters") return intervalRepositoryLetters.findMinLetters();
        return (List<String>) ResponseEntity.badRequest();
    }


}