package com.manv.itk_tasks_24._3.__LocalDateTime.controller;


import com.manv.itk_tasks_24._3.__LocalDateTime.DateTimeContainer;
import com.manv.itk_tasks_24._3.__LocalDateTime.service.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/dates")
public class DateTimeController {
    private final DateTimeService dateTimeService;

    @Autowired
    public DateTimeController(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @GetMapping
    public ResponseEntity<DateTimeContainer> getDateTime() {
        //заглушка
        return  ResponseEntity.ok(dateTimeService.convertLocalDateTimeToString(
                LocalDateTime.of(2025,3,24,17,36,30,212_000_000)));
    }
}
