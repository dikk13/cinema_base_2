package com.kata.cinema.base.webapp.controllers.publicist;

import com.kata.cinema.base.service.entity.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/publicist")
@AllArgsConstructor
public class PublicistQuestionPoolRestController {

    private final QuestionService questionService;

    @DeleteMapping("/question/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") Long questionId) {
        Long newsId = questionService.getAll().
        questionService.deleteQuestionWithAnswersAndResults(questionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
