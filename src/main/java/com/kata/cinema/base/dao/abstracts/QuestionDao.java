package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.models.Question;


public interface QuestionDao extends AbstractDao <Long, Question>{
    void deleteQuestionWithAnswersAndResultsById(Long questionId);
}
