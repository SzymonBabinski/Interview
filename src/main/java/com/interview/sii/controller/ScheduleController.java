package com.interview.sii.controller;

import com.interview.sii.model.LecturesSchedule;
import com.interview.sii.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ScheduleController {

    ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<LecturesSchedule>> getAllBooks() {
        return ResponseEntity.ok().body(scheduleService.findAll());
    }


}
