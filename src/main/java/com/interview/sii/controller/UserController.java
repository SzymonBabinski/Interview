package com.interview.sii.controller;

import com.interview.sii.exceptions.UserNotFoundException;
import com.interview.sii.model.Lecture;
import com.interview.sii.model.User;
import com.interview.sii.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/lectures/{login}")
    public ResponseEntity<Set<Lecture>> getUserLectures(@PathVariable String login) {
        try {
            return ResponseEntity.ok(userService.getUserLectures(login));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users/")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

}
