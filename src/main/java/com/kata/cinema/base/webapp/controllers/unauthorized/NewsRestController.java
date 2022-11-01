package com.kata.cinema.base.webapp.controllers.unauthorized;


import com.kata.cinema.base.dto.response.CommentNewsResponseDto;
import com.kata.cinema.base.dto.response.CommentsResponseDto;
import com.kata.cinema.base.dto.response.NewsBodyResponseDto;
import com.kata.cinema.base.mappers.CommentsMapper;
import com.kata.cinema.base.service.dto.NewsBodyResponseDtoService;
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
    private final NewsBodyResponseDtoService newsBodyResponseDtoService;

    public NewsRestController(CommentsService commentsService, CommentsMapper commentsMapper,
                              NewsBodyResponseDtoService newsBodyResponseDtoService) {
        this.commentsService = commentsService;
        this.commentsMapper = commentsMapper;
        this.newsBodyResponseDtoService = newsBodyResponseDtoService;
    }

    @GetMapping("/{id}/comments")
    public List<CommentsResponseDto> getCommentsResponseDtoById(@PathVariable("id") long newsId) {
        return commentsMapper.toDTOList(commentsService.getAllCommentsByNewsId(newsId));
    }

    @GetMapping("/{id}")
    public NewsBodyResponseDto getNewsResponseDtoById(@PathVariable("id") long id) {
        return newsBodyResponseDtoService.getNewsBodyResponseDtoById(id);
    }

//    @GetMapping("/{id}/comments")
//    public List<CommentNewsResponseDto> getCommentNewsResponseDtoList(@PathVariable("id") long id) {
//        return null;
//    }

}
