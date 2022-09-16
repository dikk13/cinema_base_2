package com.kata.cinema.base.webapp.controllers.user;


import com.kata.cinema.base.dto.CommentsRequestDto;
import com.kata.cinema.base.mappers.CommentsMapper;
import com.kata.cinema.base.models.Comments;
import com.kata.cinema.base.service.abstracts.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/news/{id}")
public class UserNewsRestController {

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private CommentsService commentsService;

    @PostMapping("/comments")
    void addComments(@PathVariable("id") long newsId, @RequestParam("userId") long userId,
                     @RequestBody CommentsRequestDto commentsRequestDto) {
        Comments comments = commentsMapper.toComments(commentsRequestDto);
        commentsService.create(comments, newsId, userId);
    }
}
