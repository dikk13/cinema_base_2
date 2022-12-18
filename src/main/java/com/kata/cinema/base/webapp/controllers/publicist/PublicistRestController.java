package com.kata.cinema.base.webapp.controllers.publicist;

import com.kata.cinema.base.dto.request.QuestionRequestDto;
import com.kata.cinema.base.mappers.QuestionMapper;
import com.kata.cinema.base.models.News;
import com.kata.cinema.base.models.Question;
import com.kata.cinema.base.service.entity.NewsService;
import com.kata.cinema.base.service.entity.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/publicist")
@AllArgsConstructor
public class PublicistRestController {

    private final QuestionService questionService;
    private final NewsService newsService;
    private final QuestionMapper questionMapper;

    @PutMapping("/news/{newsId}/questions/{questionId}")
    public ResponseEntity<Void> putQuestions(QuestionRequestDto questions,
                                             @PathVariable("newsId") Long newsId,
                                             @PathVariable("questionId") String questionId) {
        News news = newsService.getNewsById(newsId);
        Question question = questionMapper.toQuestion(questions);
        question.setNews(news);
        question.setAnswers(questionMapper.answerRequestDtoListToAnswerList(questions.getAnswers()));
        question.setResults(questionMapper.resultRequestDtoListToResultList(questions.getResults()));
        questionService.create(question);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
