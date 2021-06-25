package com.interview.sii.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;
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

    public Lecture(int id, String title, int maxMembers) {
        this.id = id;
        this.title = title;
        this.maxMembers = maxMembers;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return id == lecture.id &&
                maxMembers == lecture.maxMembers &&
                Objects.equals(title, lecture.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, maxMembers);
    }
}
