package com.interview.sii.service;

import com.interview.sii.exceptions.LectureNotFoundException;
import com.interview.sii.exceptions.MaximumParticipantsException;
import com.interview.sii.model.LoginForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
        LoginForm existingUser = new LoginForm("user1", "user1@gmail.pl");
        assertThrows(MaximumParticipantsException.class, () -> lectureService.makeReservation(1, existingUser));
    }


}