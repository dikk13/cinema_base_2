package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.dto.request.QuestionAnswerRequestDto;
import com.kata.cinema.base.models.Answer;

public interface AnswerService extends AbstractService<Long, Answer> {

    Integer countRightAnswersByQuestionAnswerRequestDto(QuestionAnswerRequestDto requestDto);

}
