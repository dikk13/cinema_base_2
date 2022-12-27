package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.Question;

import java.util.List;


public interface QuestionDao extends AbstractDao<Long, Question>{

    void deleteQuestionWithAnswersAndResultsById(Long questionId);

    List<Question> getAllQuestionsByNewsId(Long id);

}
