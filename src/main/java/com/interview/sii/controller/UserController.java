package com.interview.sii.controller;

import com.interview.sii.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

}
