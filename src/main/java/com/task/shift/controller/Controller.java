package com.task.shift.controller;

import java.util.List;
import java.util.Objects;

import com.task.shift.repository.*;
import static com.task.shift.service.IntervalService.getMergedListDigits;
import static com.task.shift.service.IntervalService.getMergedListLetters;



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



@RestController
@RequestMapping("/api/v1/intervals")
public class Controller {
    @Autowired
    private IntervalRepositoryDigits intervalRepositoryDigits;
    @Autowired
    private IntervalRepositoryLetters intervalRepositoryLetters;
    @PostMapping("/merge")
    public ResponseEntity<HttpStatus> mergeIntervals(@RequestParam(defaultValue = "digits") String kind,
                                                     @RequestBody List<List<String>> intervals){
        if(intervals.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (Objects.equals(kind, "digits")) {
            //Объединяем интервалы и сохраняем в БД
            intervalRepositoryDigits.saveAll(getMergedListDigits(intervals));
        } else if (Objects.equals(kind, "letters")) {
            //Объединяем интервалы и сохраняем в БД
            intervalRepositoryLetters.saveAll(getMergedListLetters(intervals));
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/min")
    @ResponseBody
    public List<?> getMinInterval(@RequestParam(defaultValue = "digits") String kind) {

        if (Objects.equals(kind, "digits"))
            return intervalRepositoryDigits.findMin().toList();

        else if (Objects.equals(kind, "letters"))
            return intervalRepositoryLetters.findMin().toList();

        return (List<?>) ResponseEntity.badRequest();
    }


}