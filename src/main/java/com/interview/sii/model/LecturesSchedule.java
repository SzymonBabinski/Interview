package com.interview.sii.model;

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

}
