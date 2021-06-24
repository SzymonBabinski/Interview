package com.interview.sii.service;

import com.interview.sii.exceptions.UserNotFoundException;
import com.interview.sii.model.Lecture;

import java.util.Set;

public interface UserService {
    Set<Lecture> getUserLectures(String login) throws UserNotFoundException;
}
