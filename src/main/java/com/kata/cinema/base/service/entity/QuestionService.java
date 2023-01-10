package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.Question;

import java.util.List;

public interface QuestionService extends AbstractService<Long, Question> {

    void deleteQuestionWithAnswersAndResults(Long newsId, Long questionId);

    List<Question> getAllQuestionsByNewsId(Long id);

}
