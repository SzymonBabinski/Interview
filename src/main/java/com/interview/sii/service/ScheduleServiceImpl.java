package com.interview.sii.service;

import com.interview.sii.model.LecturesSchedule;
import com.interview.sii.repository.LectureScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final LectureScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(LectureScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<LecturesSchedule> findAll() {
        return scheduleRepository.findAll();
    }
}
