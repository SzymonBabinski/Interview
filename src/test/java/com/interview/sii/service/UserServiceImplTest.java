package com.interview.sii.service;

import com.interview.sii.exceptions.UserAlreadyExistsException;
import com.interview.sii.exceptions.UserNotFoundException;
import com.interview.sii.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

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
        assertEquals(Collections.EMPTY_SET, userService.getUserLectures("user6"));
    }

    @Test
    void shouldThrowUserAlreadyExists() {
        User existingUser = new User("user1", null, null);
        assertThrows(UserAlreadyExistsException.class, () -> userService.saveUser(existingUser));
    }

    @Test
    void shouldReturnAllUsers() {
        List<User> expectedUsers = List.of(
                new User(1, "user1", "user1@gmail.pl"),
                new User(2, "user2", "user2@gmail.pl"),
                new User(3, "user3", "user3@gmail.pl"),
                new User(4, "user4", "user4@gmail.pl"),
                new User(5, "user5", "user5@gmail.pl"),
                new User(6, "user6", "user6@gmail.pl"),
                new User(7, "user7", "user7@gmail.pl"),
                new User(8, "user8", "user8@gmail.pl")
        );

        List<User> allUsers = userService.getAllUsers();

        assertArrayEquals(expectedUsers.toArray(), allUsers.toArray());
        assertEquals(8, allUsers.size());
    }

}