package com.kata.cinema.base.webapp.controllers.user;


import com.kata.cinema.base.dto.request.CommentsRequestDto;
import com.kata.cinema.base.mappers.CommentsMapper;
import com.kata.cinema.base.models.Comment;
import com.kata.cinema.base.models.News;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.entity.CommentsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/news")
@AllArgsConstructor
public class UserNewsRestController {

    private final CommentsService commentsService;
    private final CommentsMapper commentsMapper;

    @PostMapping("/{id}/comments")
    void addComments(@PathVariable("id") long newsId, @RequestParam("userId") long userId,
                     @RequestBody CommentsRequestDto commentsRequestDto) {
        Comment comment = commentsMapper.toComments(commentsRequestDto);
        User user = new User();
        user.setId(userId);
        News news = new News();
        news.setId(newsId);
        comment.setUser(user);
        comment.setNews(news);
        commentsService.create(comment);
    }
}
