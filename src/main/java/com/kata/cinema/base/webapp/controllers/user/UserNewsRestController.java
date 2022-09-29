package com.kata.cinema.base.webapp.controllers.user;


import com.kata.cinema.base.dto.CommentsRequestDto;
import com.kata.cinema.base.mappers.CommentsMapper;
import com.kata.cinema.base.models.Comments;
import com.kata.cinema.base.models.News;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.abstracts.CommentsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/news")
public class UserNewsRestController {

    private final CommentsService commentsService;

    private final CommentsMapper commentsMapper;

    public UserNewsRestController(CommentsService commentsService, CommentsMapper commentsMapper) {
        this.commentsService = commentsService;
        this.commentsMapper = commentsMapper;
    }

    @PostMapping("/{id}/comments")
    void addComments(@PathVariable("id") long newsId, @RequestParam("userId") long userId,
                     @RequestBody CommentsRequestDto commentsRequestDto) {
        Comments comments = commentsMapper.toComments(commentsRequestDto);
        User user = new User();
        user.setId(userId);
        News news = new News();
        news.setId(newsId);
        comments.setUser(user);
        comments.setNews(news);
        commentsService.create(comments);
    }
}