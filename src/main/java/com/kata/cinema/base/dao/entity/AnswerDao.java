package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.dto.request.QuestionAnswerRequestDto;
import com.kata.cinema.base.models.Answer;

public interface AnswerDao extends AbstractDao<Long, Answer>{

    Integer countRightAnswersByQuestionAnswerRequestDto(QuestionAnswerRequestDto requestDto);

}
