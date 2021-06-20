package com.interview.sii.repository;

import com.interview.sii.model.LecturesSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureScheduleRepository extends JpaRepository<LecturesSchedule, Integer> {
}
