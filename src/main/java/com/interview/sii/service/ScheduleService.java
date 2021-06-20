package com.interview.sii.service;

import com.interview.sii.model.LecturesSchedule;

import java.util.List;

public interface ScheduleService {
    List<LecturesSchedule> findAll();
}
