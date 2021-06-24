package com.interview.sii.service;

import com.interview.sii.exceptions.UserAlreadyExistsException;
import com.interview.sii.exceptions.UserNotFoundException;
import com.interview.sii.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void userLecturesShouldHaveGivenSize() throws UserNotFoundException {

        assertEquals(2, userService.getUserLectures("user1").size());
    }

    @Test
    void shouldThrowWhenUserNotFoundException() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserLectures("wrongLogin"));
    }

    @Test
    void userLecturesShouldBeEmpty() throws UserNotFoundException {
        assertEquals(Collections.EMPTY_SET, userService.getUserLectures("user5"));
    }

    @Test
    void shouldThrowUserAlreadyExists() {
        User existingUser = new User("user1", null, null);
        assertThrows(UserAlreadyExistsException.class, () -> userService.saveUser(existingUser));
    }
}