package com.interview.sii.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "lectures_schedule")
public class LecturesSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDateTime start;
    private LocalDateTime end;

    @JsonManagedReference
    @OneToMany(mappedBy = "schedules", fetch = FetchType.EAGER)
    private Set<Lecture> lectures;

    public LecturesSchedule() {
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }
}
