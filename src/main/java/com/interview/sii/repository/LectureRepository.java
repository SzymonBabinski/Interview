package com.interview.sii.repository;

import com.interview.sii.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Integer> {
    Optional<Lecture> findById(Integer lectureId);
}