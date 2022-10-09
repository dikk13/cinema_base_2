package com.kata.cinema.base.webapp.controllers.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserProfileRestController {

    @GetMapping("/profile")
    public void getUserProfileInfo() {

    }


}
