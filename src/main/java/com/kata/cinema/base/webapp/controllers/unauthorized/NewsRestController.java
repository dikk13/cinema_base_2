package com.kata.cinema.base.webapp.controllers.unauthorized;


import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.request.QuestionAnswerRequestDto;
import com.kata.cinema.base.dto.response.*;
import com.kata.cinema.base.mappers.CommentsMapper;
import com.kata.cinema.base.mappers.QuestionMapper;
import com.kata.cinema.base.mappers.ResultMapper;
import com.kata.cinema.base.models.enums.Rubric;
import com.kata.cinema.base.service.dto.CommentNewsResponseDtoService;
import com.kata.cinema.base.service.dto.NewsBodyResponseDtoService;
import com.kata.cinema.base.service.dto.NewsResponseDtoService;
import com.kata.cinema.base.service.dto.QuestionAnswerResponseDtoService;
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
    private final QuestionAnswerResponseDtoService questionAnswerResponseDtoService;

    public NewsRestController(CommentsService commentsService, CommentsMapper commentsMapper,
                              NewsBodyResponseDtoService newsBodyResponseDtoService,
                              CommentNewsResponseDtoService commentNewsResponseDtoService,
                              NewsResponseDtoService newsResponseDtoService, QuestionService questionService,
                              QuestionMapper questionMapper, ResultMapper resultMapper, ResultService resultService,
                              QuestionAnswerResponseDtoService questionAnswerResponseDtoService) {
        this.commentsService = commentsService;
        this.commentsMapper = commentsMapper;
        this.newsBodyResponseDtoService = newsBodyResponseDtoService;
        this.commentNewsResponseDtoService = commentNewsResponseDtoService;
        this.newsResponseDtoService = newsResponseDtoService;
        this.questionService = questionService;
        this.questionMapper = questionMapper;
        this.resultMapper = resultMapper;
        this.resultService = resultService;
        this.questionAnswerResponseDtoService = questionAnswerResponseDtoService;
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
    public ResponseEntity<List<CommentNewsResponseDto>> getCommentNewsResponseDtoList(@PathVariable("id") long id) {
        return ResponseEntity.ok(commentNewsResponseDtoService.getCommentNewsResponseDtoListByNewsId(id));
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
    public ResponseEntity<List<ResultResponseDto>> getResultByRightAnswers(
            List<QuestionAnswerRequestDto> questionAnswerRequestDtos) {
        return new ResponseEntity<>(resultMapper.toDtosList(
                resultService.getResultByQuestionAnswerList(questionAnswerRequestDtos)), HttpStatus.FOUND);
    }

    @GetMapping("/question/{qid}/answers")
    public ResponseEntity<List<AnswerResponseDto>> getAnswerResponseDtoList(@PathVariable("qid") Long id) {
        return ResponseEntity.ok(questionAnswerResponseDtoService.getAnswerResponseDtoListById(id));
    }
    @GetMapping("/question/{qid}/answers/{aid}")
    public ResponseEntity<Boolean> isRightAnswer(
            @PathVariable("qid") Long questionId, @PathVariable("aid") Long answerId) {
        return ResponseEntity.ok(questionAnswerResponseDtoService.isRightAnswer(questionId, answerId));
    }
    @GetMapping("/question/{qid}")
    public ResponseEntity<QuestionAnswerResponseDto> getQuestionAnswerResponseDto(@PathVariable("qid") Long id) {
        return ResponseEntity.ok(questionAnswerResponseDtoService.getQuestionAnswerResponseDtoById(id));
    }

}
