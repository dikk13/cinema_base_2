package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.QuestionResponseDto;

import java.util.List;

public interface QuestionNewsResponseDtoService extends PaginationDtoService<QuestionResponseDto>{

    List<QuestionResponseDto> getQuestionResponseDtoByNewsId(Integer count, Long newsId);
}
