package com.interview.sii.service;

import com.interview.sii.model.LecturesSchedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class ScheduleServiceImplTest {

    @Autowired
    private ScheduleService scheduleService;
    private List<LecturesSchedule> scheduleList;

    @BeforeEach
    void init() {
        scheduleList = scheduleService.findAll();
    }

    @Test
    void scheduleListShouldHaveGivenSize() {
        Assertions.assertEquals(3, scheduleList.size());
    }

    @Test
    void scheduleListShouldHaveCorrectDates() {
        LocalDateTime startOfFirstConference = LocalDateTime.parse("2021-06-01T10:00:00");
        LocalDateTime endOfFirstConference = LocalDateTime.parse("2021-06-01T11:45:00");

        LecturesSchedule firstConference = scheduleList.get(0);


        Assertions.assertEquals(startOfFirstConference, firstConference.getStart());
        Assertions.assertEquals(endOfFirstConference, firstConference.getEnd());
    }

    @Test
    void scheduleShouldHaveGivenLectures() {
        Assertions.assertEquals(3, scheduleList.get(0).getLectures().size());
        Assertions.assertEquals(3, scheduleList.get(1).getLectures().size());
        Assertions.assertEquals(3, scheduleList.get(2).getLectures().size());
    }


}