package com.kata.cinema.base.webapp.controllers.unauthorized;


import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.response.CommentNewsResponseDto;
import com.kata.cinema.base.dto.response.CommentsResponseDto;
import com.kata.cinema.base.dto.response.NewsBodyResponseDto;
import com.kata.cinema.base.dto.response.NewsResponseDto;
import com.kata.cinema.base.mappers.CommentsMapper;
import com.kata.cinema.base.models.enums.Rubric;
import com.kata.cinema.base.service.dto.CommentNewsResponseDtoService;
import com.kata.cinema.base.service.dto.NewsBodyResponseDtoService;
import com.kata.cinema.base.service.dto.NewsResponseDtoService;
import com.kata.cinema.base.service.entity.CommentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/news")
public class NewsRestController {

    private final CommentsService commentsService;
    private final CommentsMapper commentsMapper;
    private final NewsBodyResponseDtoService newsBodyResponseDtoService;
    private final CommentNewsResponseDtoService commentNewsResponseDtoService;
    private final NewsResponseDtoService newsResponseDtoService;

    public NewsRestController(CommentsService commentsService, CommentsMapper commentsMapper,
                              NewsBodyResponseDtoService newsBodyResponseDtoService,
                              CommentNewsResponseDtoService commentNewsResponseDtoService, NewsResponseDtoService newsResponseDtoService) {
        this.commentsService = commentsService;
        this.commentsMapper = commentsMapper;
        this.newsBodyResponseDtoService = newsBodyResponseDtoService;
        this.commentNewsResponseDtoService = commentNewsResponseDtoService;
        this.newsResponseDtoService = newsResponseDtoService;
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentsResponseDto>> getCommentsResponseDtoById(@PathVariable("id") long newsId) {
        return ResponseEntity.ok(commentsMapper.toDTOList(commentsService.getAllCommentsByNewsId(newsId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsBodyResponseDto> getNewsResponseDtoById(@PathVariable("id") long id) {
        return ResponseEntity.ok(newsBodyResponseDtoService.getNewsBodyResponseDtoById(id)
                .orElseThrow(() -> new NoResultException("No entity found for query")));
    }

    @GetMapping("/{id}/comments/body")
    public List<CommentNewsResponseDto> getCommentNewsResponseDtoList(@PathVariable("id") long id) {
        return commentNewsResponseDtoService.getCommentNewsResponseDtoListByNewsId(id);
    }

    @GetMapping("/page/{pageNumber}")
    public PageDto<NewsResponseDto> getNews(
            @PathVariable("pageNumber") Integer pageNumber,
            @RequestParam(value = "itemsOnPage", required = false, defaultValue = "10") Integer itemsOnPage,
            @RequestParam(value = "rubric", required = false, defaultValue = "NEWS") Rubric rubric) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("rubric", rubric);
        return newsResponseDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);


    }


}
