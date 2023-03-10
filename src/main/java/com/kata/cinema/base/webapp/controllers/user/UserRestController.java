package com.kata.cinema.base.webapp.controllers.user;

import com.kata.cinema.base.dto.request.PasswordChangeRequestDto;
import com.kata.cinema.base.dto.request.UserRequestDto;
import com.kata.cinema.base.dto.response.UserResponseDto;
import com.kata.cinema.base.exception.CommentNotFoundException;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.models.enums.TypeRating;
import com.kata.cinema.base.service.dto.UserDtoService;
import com.kata.cinema.base.service.entity.CommentsService;
import com.kata.cinema.base.service.entity.RatingCommentService;
import com.kata.cinema.base.service.entity.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserRestController {

    private final UserDtoService userDtoService;
    private final UserService userService;
    private final RatingCommentService ratingCommentService;
    private final CommentsService commentsService;


    @GetMapping("/profile")
    public ResponseEntity<UserResponseDto> getUserProfileInfo(@AuthenticationPrincipal User currentUser) {

        Optional<User> targetUser = userService.getById(currentUser.getId());
        if (targetUser.isEmpty()) {
            throw new RuntimeException("Неверно передан id, пользователя с таким ");
        }
        return ResponseEntity.ok(targetUser.map(userDtoService::getUserResponseDto).orElse(null));
    }

    @PutMapping("/profile")
    public ResponseEntity<Void> changeUserDetails(@RequestBody UserRequestDto userRequestDto,
                                                  @AuthenticationPrincipal User currentUser) {
        userService.changeUserDetails(userRequestDto, currentUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/profile/password")
    public ResponseEntity<Void> changeUserPassword(@RequestBody PasswordChangeRequestDto passwordChangeRequestDto,
                                                   @AuthenticationPrincipal User currentUser) {
        Optional<User> targetUser = userService.getById(currentUser.getId());
        targetUser.ifPresent(user -> userService.changePassword(passwordChangeRequestDto, user));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/profile")
    public ResponseEntity<Void> disableUser(@AuthenticationPrincipal User currentUser) {
        userService.disableUser(currentUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/comment/{id}")
    public ResponseEntity<Void> rateComment(
            @PathVariable("id") Long commentId, @RequestParam(name = "rating") TypeRating rating,
            @AuthenticationPrincipal User currentUser) {
        if (commentsService.getById(commentId).isEmpty()) {
            throw new CommentNotFoundException("Comment with id not found");
        }
        ratingCommentService.createOrUpdateRatingComment(currentUser.getId(), commentId, rating);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

