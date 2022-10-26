package com.kata.cinema.base.webapp.controllers.admin;


import com.kata.cinema.base.dto.request.QuestionRequestDto;
import com.kata.cinema.base.mappers.QuestionMapper;
import com.kata.cinema.base.models.*;
import com.kata.cinema.base.service.entity.NewsService;
import com.kata.cinema.base.service.entity.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin/news")
@AllArgsConstructor
public class AdminQuestionRestController {

    private final QuestionService questionService;
    private final NewsService newsService;
    private final QuestionMapper questionMapper;

    @PostMapping("/{id}/questions")
    public void addQuestions(@PathVariable("id") Long newsId, @RequestBody List <QuestionRequestDto> questionList) {
        News news = newsService.getNewsById(newsId);
        for (QuestionRequestDto questionRequestDto: questionList){
            Question question = questionMapper.toQuestion(questionRequestDto);
            question.setNews(news);
            question.setAnswers(questionMapper.answerRequestDtoListToAnswerList(questionRequestDto.getAnswers()));
            question.setResults(questionMapper.resultRequestDtoListToResultList(questionRequestDto.getResults()));
            questionService.create(question);
        }
    }

    @DeleteMapping("{newsId}/questions/{id}")
    public void deleteQuestion(@PathVariable("newsId") Long newsId, @PathVariable("id") Long questionId) {
            questionService.deleteQuestionWithAnswersAndResults(newsId, questionId);
    }
}
