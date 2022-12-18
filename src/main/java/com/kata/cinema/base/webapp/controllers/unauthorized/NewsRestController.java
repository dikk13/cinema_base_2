package com.kata.cinema.base.webapp.controllers.unauthorized;


import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.request.QuestionAnswerRequestDto;
import com.kata.cinema.base.dto.response.*;
import com.kata.cinema.base.mappers.CommentsMapper;
import com.kata.cinema.base.mappers.QuestionAnswerMapper;
import com.kata.cinema.base.mappers.QuestionMapper;
import com.kata.cinema.base.mappers.ResultMapper;
import com.kata.cinema.base.models.enums.Rubric;
import com.kata.cinema.base.service.dto.CommentNewsResponseDtoService;
import com.kata.cinema.base.service.dto.NewsBodyResponseDtoService;
import com.kata.cinema.base.service.dto.NewsResponseDtoService;
import com.kata.cinema.base.service.entity.CommentsService;
import com.kata.cinema.base.service.entity.QuestionService;
import com.kata.cinema.base.service.entity.ResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final QuestionService questionService;
    private final QuestionMapper questionMapper;
    private final ResultMapper resultMapper;
    private final ResultService resultService;
    private final QuestionAnswerMapper questionAnswerMapper;

    public NewsRestController(CommentsService commentsService, CommentsMapper commentsMapper,
                              NewsBodyResponseDtoService newsBodyResponseDtoService,
                              CommentNewsResponseDtoService commentNewsResponseDtoService,
                              NewsResponseDtoService newsResponseDtoService, QuestionService questionService,
                              QuestionMapper questionMapper, ResultMapper resultMapper,
                              ResultService resultService, QuestionAnswerMapper questionAnswerMapper) {
        this.commentsService = commentsService;
        this.commentsMapper = commentsMapper;
        this.newsBodyResponseDtoService = newsBodyResponseDtoService;
        this.commentNewsResponseDtoService = commentNewsResponseDtoService;
        this.newsResponseDtoService = newsResponseDtoService;
        this.questionService = questionService;
        this.questionMapper = questionMapper;
        this.resultMapper = resultMapper;
        this.resultService = resultService;
        this.questionAnswerMapper = questionAnswerMapper;
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

    @GetMapping("/{id}/tests")
    public ResponseEntity<List<QuestionResponseDto>> getQuestionResponseDtoList(
            @PathVariable Long id) {
        return new ResponseEntity<>(questionMapper.toDTOList(
                questionService.getAllQuestionsByNewsId(id)), HttpStatus.FOUND);
    }

    @PostMapping("/{id}/questions/tests")
    public ResponseEntity<ResultResponseDto> getResultByRightAnswers(
            List<QuestionAnswerRequestDto> questionAnswerRequestDtos) {
        return new ResponseEntity<>(resultMapper.toDTO(
                resultService.getResultByQuestionAnswerList(questionAnswerMapper.fromDTO(
                        questionAnswerRequestDtos))), HttpStatus.FOUND);
    }

}
