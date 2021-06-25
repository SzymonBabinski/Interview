package com.interview.sii.repository;

import com.interview.sii.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {
    Optional<Lecture> findById(Integer lectureId);


    @Query(nativeQuery = true, value = "SELECT COUNT (*) FROM USERS_LECTURES ul  " +
            "INNER JOIN LECTURE l ON (ul.lecture_id = l.id)  " +
            "INNER JOIN USER user ON (ul.user_id = user.id)" +
            "WHERE l.LECTURE_SCHEDULES_ID = :scheduleId AND user.login = :userId")
    Integer getUserLecturesCountOnPath(String userId, Integer scheduleId);
}