package com.kata.cinema.base.webapp.controllers.user;


import com.kata.cinema.base.dao.abstracts.CommentsDao;
import com.kata.cinema.base.dto.CommentsRequestDto;
import com.kata.cinema.base.mappers.CommentsMapper;
import com.kata.cinema.base.models.Comments;
import com.kata.cinema.base.models.News;
import com.kata.cinema.base.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/news/{id}/comments")
public class UserNewsRestController {

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private CommentsDao commentsDao;

    @PostMapping("")
    void addComments(@PathVariable("id") long newsId, @RequestParam(name = "userId") long userId,
                     @RequestBody CommentsRequestDto commentsRequestDto) {
        Comments comments = commentsMapper.toComments(commentsRequestDto);
        User user = new User();
        user.setId(userId);
        News news = new News();
        news.setId(newsId);
        comments.setUser(user);
        comments.setNews(news);
        commentsDao.create(comments);
    }
}
