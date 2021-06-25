package com.interview.sii.service;

import com.interview.sii.exceptions.*;
import com.interview.sii.model.Lecture;
import com.interview.sii.model.LoginForm;
import com.interview.sii.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class LectureServiceImplTest {
    @Autowired
    LectureService lectureService;
    @Autowired
    UserService userService;

    @Test
    void shouldThrowLectureNotFoundException() {
        assertThrows(LectureNotFoundException.class, () -> lectureService.makeReservation(0, new LoginForm()));
    }

    @Test
    void shouldThrowMaximumParticipantsException() {
        assertThrows(MaximumParticipantsException.class, () -> lectureService.makeReservation(1, new LoginForm()));
    }

    @Test
    void shouldThrowUserAlreadyExistsException() {
        LoginForm existingUser = new LoginForm("user1", "wrongEmail@gmail.pl");
        assertThrows(UserAlreadyExistsException.class, () -> lectureService.makeReservation(3, existingUser));
    }

    @Test
    void shouldThrowMaximumLecturesOnPath() {
        LoginForm existingUser = new LoginForm("user7", "user7@gmail.pl");
        assertThrows(MaximumLecturesOnPath.class, () -> lectureService.makeReservation(8, existingUser));
    }

    @Test
    void shouldThrowUserNotFoundException() {
        LoginForm wrongUser = new LoginForm("wrongLogin", "wrongEmail");
        assertThrows(UserNotFoundException.class, () -> lectureService.deleteReservation(1, wrongUser));
    }

    @Test
    void emailSendTest()
            throws UserAlreadyExistsException
            , MaximumParticipantsException, LectureNotFoundException, MaximumLecturesOnPath {

        LoginForm loginForm = new LoginForm("newTestUser", "newTestUser@email.com");


        long linesBeforeMakingReservation = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("powiadomienia.txt"))) {
            while (reader.readLine() != null) linesBeforeMakingReservation++;
        } catch (IOException e) {
            e.printStackTrace();
        }

        lectureService.makeReservation(3, loginForm);
        lectureService.makeReservation(6, loginForm);
        lectureService.makeReservation(9, loginForm);

        long linesAfterMakingReservation = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("powiadomienia.txt"))) {
            while (reader.readLine() != null) linesAfterMakingReservation++;
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(linesBeforeMakingReservation + 3, linesAfterMakingReservation);
    }

    @Test
    void reservationTest() throws UserNotFoundException {

        LoginForm loginForm = new LoginForm("newTestUser", "newTestUser@email.com");

        User userAfterMadeReservation;
        if (userService.getUserByLoginAndEmail(loginForm.getLogin(), loginForm.getEmail()).isPresent()) {
            userAfterMadeReservation = userService.getUserByLoginAndEmail(
                    loginForm.getLogin(), loginForm.getEmail()
            ).get();
        } else {
            throw new UserNotFoundException("User with login newTestUser and email: newTestUser@email.com not found!");
        }

        Lecture lecture1 = new Lecture(3, "Programowanie w Python", 5);
        Lecture lecture2 = new Lecture(6, "Testy w Python", 5);
        Lecture lecture3 = new Lecture(9, "Wstep do Kotlin", 5);

        Set<Lecture> expectedSavedLectures = Set.of(lecture1, lecture2, lecture3);
        boolean checkIfReservationsSaved = userAfterMadeReservation.getLectures().containsAll(expectedSavedLectures);

        assertTrue(checkIfReservationsSaved);

        lectureService.deleteReservation(3, loginForm);

        Set<Lecture> deletedLecture = Set.of(lecture1);

        User userAfterRemovedReservation;
        if (userService.getUserByLoginAndEmail(loginForm.getLogin(), loginForm.getEmail()).isPresent()) {
            userAfterRemovedReservation = userService
                    .getUserByLoginAndEmail(
                            loginForm.getLogin(), loginForm.getEmail()
                    ).get();
        } else {
            throw new UserNotFoundException("User with login " +
                    loginForm.getLogin() + " and email: " +
                    loginForm.getEmail() + " not found!");
        }

        boolean checkIfReservationsDeleted = !userAfterRemovedReservation.getLectures().containsAll(deletedLecture);

        assertTrue(checkIfReservationsDeleted);
    }


}