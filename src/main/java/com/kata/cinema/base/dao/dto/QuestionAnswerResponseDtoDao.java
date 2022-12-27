package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.AnswerResponseDto;
import com.kata.cinema.base.dto.response.QuestionAnswerResponseDto;

import java.util.List;

public interface QuestionAnswerResponseDtoDao {
    QuestionAnswerResponseDto getQuestionAnswerResponseDtoById(Long id);
    List<AnswerResponseDto> getAnswerResponseDtoListById(Long id);
    Boolean isRightAnswer(Long questionId, Long answerId);
}
