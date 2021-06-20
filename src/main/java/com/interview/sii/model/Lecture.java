package com.interview.sii.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private int maxMembers;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "lecture_schedules_id", nullable = false)
    private LecturesSchedule schedules;

    public Lecture() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxUsers() {
        return maxMembers;
    }

    public LecturesSchedule getSchedules() {
        return schedules;
    }

}
