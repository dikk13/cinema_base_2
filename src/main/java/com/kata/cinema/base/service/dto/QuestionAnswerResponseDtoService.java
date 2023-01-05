package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.AnswerResponseDto;
import com.kata.cinema.base.dto.response.QuestionAnswerResponseDto;

import java.util.List;

public interface QuestionAnswerResponseDtoService {
    QuestionAnswerResponseDto getQuestionAnswerResponseDtoById(Long id);
    List<AnswerResponseDto> getAnswerResponseDtoListById(Long id);
    Boolean isRightAnswer(Long questionId, Long answerId);
}
