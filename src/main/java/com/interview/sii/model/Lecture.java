package com.interview.sii.model;

import javax.persistence.*;

@Entity
@Table(name = "lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private int maxMembers;

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
