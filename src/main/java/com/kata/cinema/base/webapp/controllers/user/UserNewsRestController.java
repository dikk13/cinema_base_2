package com.kata.cinema.base.webapp.controllers.user;


import com.kata.cinema.base.dto.CommentsRequestDto;
import com.kata.cinema.base.service.abstracts.CommentsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/news/{id}")
public class UserNewsRestController {

    private final CommentsService commentsService;

    public UserNewsRestController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("/comments")
    void addComments(@PathVariable("id") long newsId, @RequestParam("userId") long userId,
                     @RequestBody CommentsRequestDto commentsRequestDto) {
        commentsService.create(commentsRequestDto, userId, newsId);
    }
}
