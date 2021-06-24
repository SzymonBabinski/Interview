package com.interview.sii.service;

import com.interview.sii.exceptions.UserAlreadyExistsException;
import com.interview.sii.exceptions.UserNotFoundException;
import com.interview.sii.model.Lecture;
import com.interview.sii.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    Set<Lecture> getUserLectures(String login) throws UserNotFoundException;

    User saveUser(User user) throws UserAlreadyExistsException;

    List<User> getAllUsers();
}
