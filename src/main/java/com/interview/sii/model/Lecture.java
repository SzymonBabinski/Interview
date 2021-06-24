package com.interview.sii.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lecture")
@JsonIgnoreProperties({"users", "maxMembers", "users"})
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

    @ManyToMany(mappedBy = "lectures")
    private Set<User> users;

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

    public Set<User> getUsers() {
        return users;
    }
}
