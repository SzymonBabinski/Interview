package com.interview.sii.service;

import com.interview.sii.exceptions.*;
import com.interview.sii.model.Lecture;
import com.interview.sii.model.LoginForm;
import com.interview.sii.model.User;
import com.interview.sii.repository.LectureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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
            throws LectureNotFoundException, MaximumParticipantsException, UserAlreadyExistsException, MaximumLecturesOnPath {

        Lecture lecture;
        if (lectureRepository.findById(lectureId).isPresent()) {
            lecture = lectureRepository.findById(lectureId).get();
        } else {
            throw new LectureNotFoundException("Lecture with id " + lectureId + " not found!");
        }

        if (lecture.getUsers().size() >= lecture.getMaxUsers()) {
            throw new MaximumParticipantsException("Maximum participants for lecture " + lectureId + " reached!");
        }


        User user;
        if (userService.getUserByLoginAndEmail(loginForm.getLogin(), loginForm.getEmail()).isPresent()) {

            if (lectureRepository.getUserLecturesCountOnPath(loginForm.getLogin(), lecture.getSchedules().getId()) > 0) {
                throw new MaximumLecturesOnPath("You already have already registerer lecture on this path!");
            }

            user = userService.getUserByLoginAndEmail(loginForm.getLogin(), loginForm.getEmail()).get();
            user.getLectures().add(lecture);
        } else {
            user = new User(loginForm.getLogin(), loginForm.getEmail(), Set.of(lecture));
        }
        userService.saveUser(user);

        LocalDateTime sendEmailTime = LocalDateTime.now();

        File file = new File("powiadomienia.txt");

        try (FileWriter fileWriter = new FileWriter(file, true);
             PrintWriter writer = new PrintWriter(fileWriter)) {

            writer.println("Data wysłania: " + sendEmailTime
                    + " Odbiorca: " + loginForm.getEmail()
                    + " Treść: Drogi użytkowniku: " + loginForm.getLogin()
                    + " dziękujemy za dokonanie rezerwacji na prelekcje " + lecture.getTitle()
                    + ", życzymy udanej zabawy!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteReservation(Integer lectureId, LoginForm loginForm) throws UserNotFoundException {
        if (userService.getUserByLoginAndEmail(loginForm.getLogin(), loginForm.getEmail()).isEmpty()) {
            throw new UserNotFoundException("User with login " + loginForm.getLogin() + "and email: " + loginForm.getEmail() + " not found!");
        }

        var user = userService.getUserByLoginAndEmail(loginForm.getLogin(), loginForm.getEmail()).get();
        lectureRepository.deleteReservation(lectureId, user.getId());
    }


}
