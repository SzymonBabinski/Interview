package com.interview.sii.service;

import com.interview.sii.exceptions.*;
import com.interview.sii.model.LoginForm;

public interface LectureService {
    void makeReservation(Integer lectureId, LoginForm loginForm)
            throws LectureNotFoundException, MaximumParticipantsException, UserAlreadyExistsException, MaximumLecturesOnPath;

    void deleteReservation(Integer lectureId, LoginForm loginForm) throws UserNotFoundException;

}
