package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.dto.request.QuestionAnswerRequestDto;
import com.kata.cinema.base.models.Result;

import java.util.List;

public interface ResultDao extends AbstractDao<Long, Result>{

    List<Result> getResultByQuestionAnswerList(List<QuestionAnswerRequestDto> questionAnswerRequestDtoList);

}
