package com.interview.sii.service;

import com.interview.sii.exceptions.UserNotFoundException;
import com.interview.sii.model.Lecture;
import com.interview.sii.model.User;
import com.interview.sii.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<Lecture> getUserLectures(String login) throws UserNotFoundException {
        Optional<User> user = userRepository.getByLogin(login);

        if (user.isPresent()) {
            return user.get().getLectures();
        } else {
            throw new UserNotFoundException("User with login " + login + " not found!");
        }
    }
}
