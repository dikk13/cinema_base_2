package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.dto.QuestionRequestDto;
import com.kata.cinema.base.models.Answer;
import com.kata.cinema.base.models.Question;
import com.kata.cinema.base.models.Result;

import java.util.List;

public interface QuestionService extends AbstractService <Long, Question> {

    List<Answer> toAnswer(QuestionRequestDto questionRequestDto);

    List<Result> toResult(QuestionRequestDto questionRequestDto);

    void deleteQuestionWithAnswersAndResults(Long questionId);
}
