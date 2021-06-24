package com.interview.sii.service;

import com.interview.sii.exceptions.UserAlreadyExistsException;
import com.interview.sii.exceptions.UserNotFoundException;
import com.interview.sii.model.Lecture;
import com.interview.sii.model.User;
import com.interview.sii.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

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

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if (userRepository.getUserByLogin(user.getLogin()).isPresent()) {
            throw new UserAlreadyExistsException("Podany login " + user.getLogin() + " jest juz zajety! ");
        } else {
            userRepository.save(user);
            return user;
        }
    }
}
