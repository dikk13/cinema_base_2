package com.kata.cinema.base.webapp.controllers.unauthorized;


import com.kata.cinema.base.dto.response.CommentsResponseDto;
import com.kata.cinema.base.mappers.CommentsMapper;
import com.kata.cinema.base.service.entity.CommentsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsRestController {

    private final CommentsService commentsService;
    private final CommentsMapper commentsMapper;

    public NewsRestController(CommentsService commentsService, CommentsMapper commentsMapper) {
        this.commentsService = commentsService;
        this.commentsMapper = commentsMapper;
    }

    @GetMapping("/{id}/comments")
    public List<CommentsResponseDto> getCommentsResponseDtoById(@PathVariable("id") long newsId) {
        return commentsMapper.toDTOList(commentsService.getAllCommentsByNewsId(newsId));
    }
}
