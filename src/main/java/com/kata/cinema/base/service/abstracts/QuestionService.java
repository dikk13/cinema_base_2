package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.models.Question;


public interface QuestionService extends AbstractService <Long, Question> {

    void deleteQuestionWithAnswersAndResults(Long newsId, Long questionId);
}
