package com.kata.cinema.base.webapp.controllers.user;

import com.kata.cinema.base.dto.request.PasswordChangeRequestDto;
import com.kata.cinema.base.dto.request.UserRequestDto;
import com.kata.cinema.base.dto.response.UserResponseDto;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.abstracts.UserDtoService;
import com.kata.cinema.base.service.abstracts.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserProfileRestController {

    private final UserDtoService userDtoService;
    private final UserService userService;

    public UserProfileRestController(UserDtoService userDtoService, UserService userService) {
        this.userDtoService = userDtoService;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public UserResponseDto getUserProfileInfo(
            @AuthenticationPrincipal User currentUser) {
        return userDtoService.getUserResponseDto(currentUser);
    }

    @PutMapping("/profile")
    public void changeUserDetails(
            @RequestBody UserRequestDto userRequestDto,
            @AuthenticationPrincipal User currentUser) {
        userService.changeUserDetails(userRequestDto, currentUser);
    }

    @PutMapping("/profile/password")
    public void changeUserPassword(
            @RequestBody PasswordChangeRequestDto passwordChangeRequestDto,
            @AuthenticationPrincipal User currentUser) {
        userService.changePassword(passwordChangeRequestDto, currentUser);
    }

    @DeleteMapping("/profile")
    public void disableUser(@AuthenticationPrincipal User currentUser) {
        userService.disableUser(currentUser);
    }

}

