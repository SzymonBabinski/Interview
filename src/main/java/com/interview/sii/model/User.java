package com.interview.sii.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"lectures"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String login;

    private String email;

    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_lectures",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "lecture_id")}
    )
    private Set<Lecture> lectures;

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Set<Lecture> getLectures() {
        return lectures;
    }
}
