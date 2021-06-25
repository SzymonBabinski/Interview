package com.interview.sii.service;

import com.interview.sii.exceptions.*;
import com.interview.sii.model.LoginForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class LectureServiceImplTest {
    @Autowired
    LectureService lectureService;

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
    void emailSendTest()
            throws IOException, UserAlreadyExistsException
            , MaximumParticipantsException, LectureNotFoundException, MaximumLecturesOnPath {

        LoginForm loginForm = new LoginForm("newUser", "newUser@email.com");
        lectureService.makeReservation(2, loginForm);
        BufferedReader bufferedReader = new BufferedReader(new FileReader("powiadomienia.txt"));

        String email = bufferedReader.readLine();

        assertNotNull(email);
    }


}