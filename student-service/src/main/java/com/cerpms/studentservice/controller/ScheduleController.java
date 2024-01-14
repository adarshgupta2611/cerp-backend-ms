package com.cerpms.studentservice.controller;

import com.cerpms.studentservice.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student-service/schedule")
@CrossOrigin(origins = "http://localhost:3000/")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/{courseName}")
    public ResponseEntity<?> getSchedule(@PathVariable String courseName) {
        return ResponseEntity.ok(scheduleService.getSchedule(courseName));
    }
}