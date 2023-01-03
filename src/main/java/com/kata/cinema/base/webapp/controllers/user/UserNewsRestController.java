package com.kata.cinema.base.webapp.controllers.user;

import com.kata.cinema.base.dto.request.CommentsRequestDto;
import com.kata.cinema.base.mappers.CommentsMapper;

import com.kata.cinema.base.models.News;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.entity.CommentsService;
import com.kata.cinema.base.service.entity.UserService;
import lombok.AllArgsConstructor;
import org.mapstruct.ap.shaded.freemarker.core.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user/news")
@AllArgsConstructor
public class UserNewsRestController {

    private final CommentsService commentsService;
    private final CommentsMapper commentsMapper;
    private final UserService userService;

    @PostMapping("/{id}/comments")
    public ResponseEntity<Void> addComments(@PathVariable("id") long newsId,
                                     @AuthenticationPrincipal User principal,
                                     @RequestBody CommentsRequestDto commentsRequestDto) {
        Comment comment = commentsMapper.toComments(commentsRequestDto);
        String username = principal.getEmail();
        Optional<User> userOptional = userService.getByEmail(username);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Неверно передан id, пользователя с таким id нету ");
        }
        News news = new News();
        news.setId(newsId);
        comment.setUser(userOptional.get());
        comment.setNews(news);
        commentsService.create(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
