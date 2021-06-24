package com.interview.sii.controller;

import com.interview.sii.exceptions.LectureNotFoundException;
import com.interview.sii.exceptions.MaximumParticipantsException;
import com.interview.sii.exceptions.UserAlreadyExistsException;
import com.interview.sii.model.LoginForm;
import com.interview.sii.service.LectureService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class LectureController {

    LectureService lectureService;

    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    @PostMapping("/lectures/{lectureId}")
    ResponseEntity makeReservation(@PathVariable Integer lectureId, @RequestBody LoginForm loginForm) {
        try {
            lectureService.makeReservation(lectureId, loginForm);
            return ResponseEntity.ok().build();
        } catch (LectureNotFoundException | MaximumParticipantsException | UserAlreadyExistsException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
