package com.kata.cinema.base.webapp.controllers.user;


import com.kata.cinema.base.dto.request.CommentsRequestDto;
import com.kata.cinema.base.mappers.CommentsMapper;
import com.kata.cinema.base.models.Comment;
import com.kata.cinema.base.models.News;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.entity.CommentsService;
import com.kata.cinema.base.service.entity.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/user/news")
@AllArgsConstructor
public class UserNewsRestController {

    private final CommentsService commentsService;
    private final CommentsMapper commentsMapper;

    private final UserService userService;

    @PostMapping("/{id}/comments")
    void addComments(@PathVariable("id") long newsId, Principal principal,
                     @RequestBody CommentsRequestDto commentsRequestDto) {
        Comment comment = commentsMapper.toComments(commentsRequestDto);
        String username = principal.getName();
        User user = userService.getByEmail(username).get();
        News news = new News();
        news.setId(newsId);
        comment.setUser(user);
        comment.setNews(news);
        commentsService.create(comment);
    }
}
