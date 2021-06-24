package com.interview.sii.service;

import com.interview.sii.exceptions.LectureNotFoundException;
import com.interview.sii.exceptions.MaximumParticipantsException;
import com.interview.sii.exceptions.UserAlreadyExistsException;
import com.interview.sii.model.LoginForm;

public interface LectureService {
    void makeReservation(Integer lectureId, LoginForm loginForm)
            throws LectureNotFoundException, MaximumParticipantsException, UserAlreadyExistsException;
}
