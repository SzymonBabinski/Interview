package com.interview.sii.controller;

import com.interview.sii.exceptions.*;
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

    @PostMapping("/lectures/{lectureId}/reservations")
    public ResponseEntity<String> makeReservation(@PathVariable Integer lectureId, @RequestBody LoginForm loginForm) {
        try {
            lectureService.makeReservation(lectureId, loginForm);
            return ResponseEntity.ok().build();
        } catch (LectureNotFoundException | MaximumParticipantsException | UserAlreadyExistsException | MaximumLecturesOnPath e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/lectures/{lectureId}/reservations")
    public ResponseEntity<String> deleteReservation(@PathVariable Integer lectureId, @RequestBody LoginForm loginForm) {
        try {
            lectureService.deleteReservation(lectureId, loginForm);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
