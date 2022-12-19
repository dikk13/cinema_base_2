package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.dto.request.QuestionAnswerRequestDto;
import com.kata.cinema.base.models.Result;

import java.util.List;

public interface ResultService extends AbstractService<Long, Result> {

    List<Result> getResultByQuestionAnswerList(List<QuestionAnswerRequestDto> questionAnswerRequestDtoList);

}