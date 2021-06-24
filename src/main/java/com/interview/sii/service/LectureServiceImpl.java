package com.interview.sii.service;

import com.interview.sii.exceptions.LectureNotFoundException;
import com.interview.sii.exceptions.MaximumParticipantsException;
import com.interview.sii.exceptions.UserAlreadyExistsException;
import com.interview.sii.model.Lecture;
import com.interview.sii.model.LoginForm;
import com.interview.sii.model.User;
import com.interview.sii.repository.LectureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Set;

@Service
@Transactional
public class LectureServiceImpl implements LectureService {

    LectureRepository lectureRepository;
    UserService userService;

    public LectureServiceImpl(LectureRepository lectureRepository, UserService userService) {
        this.lectureRepository = lectureRepository;
        this.userService = userService;
    }


    public void makeReservation(Integer lectureId, LoginForm loginForm)
            throws LectureNotFoundException, MaximumParticipantsException, UserAlreadyExistsException {

        Lecture lecture;
        if (lectureRepository.findById(lectureId).isPresent()) {
            lecture = lectureRepository.findById(lectureId).get();
        } else {
            throw new LectureNotFoundException("Lecture with id " + lectureId + " not found!");
        }

        if (lecture.getUsers().size() >= lecture.getMaxUsers()) {
            throw new MaximumParticipantsException("Maximum participants for lecture " + lectureId + " reached!");
        }

        User user = new User(loginForm.getLogin(), loginForm.getEmail(), Set.of(lecture));
        userService.saveUser(user);

        LocalDateTime sendEmailTime = LocalDateTime.now();

        try (FileWriter fileWriter = new FileWriter("powiadomienia.txt")) {
            PrintWriter writer = new PrintWriter(fileWriter);
            writer.print("Data wysłania: " + sendEmailTime
                    + " Odbiorca: " + loginForm.getEmail()
                    + " Treść: Drogi użytkowniku: " + loginForm.getLogin()
                    + " dziękujemy za dokonanie rezerwacji na prelekcje " + lecture.getTitle()
                    + ", życzymy udanej zabawy!");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
