package com.kata.cinema.base.webapp.controllers.publicist;

import com.kata.cinema.base.dto.request.NewsRequestDto;
import com.kata.cinema.base.dto.request.QuestionRequestDto;
import com.kata.cinema.base.exception.NewsIdNotFoundException;
import com.kata.cinema.base.mappers.NewsMapper;
import com.kata.cinema.base.mappers.QuestionMapper;
import com.kata.cinema.base.models.News;
import com.kata.cinema.base.models.Question;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.entity.NewsService;
import com.kata.cinema.base.service.entity.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/publicist/news")
@AllArgsConstructor
public class PublicistNewsRestController {

    private final QuestionService questionService;
    private final NewsService newsService;
    private final QuestionMapper questionMapper;
    private final NewsMapper newsMapper;


    @PutMapping("/{newsId}/questions/{questionId}")
    public ResponseEntity<Void> putQuestions(QuestionRequestDto questions,
                                             @PathVariable("newsId") Long newsId,
                                             @PathVariable("questionId") Long questionId) {
        Question question = questionMapper.toQuestion(questions);
        question.setId(questionId);
        question.setNews(newsService.getNewsById(newsId));
        question.setAnswers(questionMapper.answerRequestDtoListToAnswerList(questions.getAnswers()));
        question.setResults(questionMapper.resultRequestDtoListToResultList(questions.getResults()));
        questionService.create(question);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> postNewNews(@RequestBody NewsRequestDto newsRequestDto,
                                            @AuthenticationPrincipal User user) {
        News news = newsMapper.toNews(newsRequestDto);
        news.setUser(user);
        news.setDate(LocalDateTime.now());
        newsService.create(news);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable("id") Long id,
                                           @AuthenticationPrincipal User user) {
        Optional<News> optionalNews =newsService.getById(id);
        if (optionalNews.isEmpty()) {
            throw new NewsIdNotFoundException("News with id is not found");
        }
        News news = optionalNews.get();
        if (news.getUser().getId().longValue() != user.getId().longValue()) {
            throw new RuntimeException("Current user is not allowed to delete this news");
        }
        newsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateNews(@PathVariable("id") Long id,
                                           @RequestBody NewsRequestDto newsRequestDto,
                                           @AuthenticationPrincipal User user) {
        Optional<News> optionalNews = newsService.getById(id);
        if (optionalNews.isEmpty()) {
            throw new NewsIdNotFoundException("News with id is not found");
        }
        News news = optionalNews.get();
        if (news.getUser().getId().longValue() != user.getId().longValue()) {
            throw new RuntimeException("Current user is not allowed to update this news");
        }
        news.setRubric(newsRequestDto.getRubric());
        news.setTitle(newsRequestDto.getTitle());
        news.setHtmlBody(newsRequestDto.getHtmlBody());
        news.setDate(LocalDateTime.now());
        newsService.update(news);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
