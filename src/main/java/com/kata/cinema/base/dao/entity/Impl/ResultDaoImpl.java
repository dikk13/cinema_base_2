package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.ResultDao;
import com.kata.cinema.base.dto.request.QuestionAnswerRequestDto;
import com.kata.cinema.base.models.Result;
import com.kata.cinema.base.service.entity.AnswerService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ResultDaoImpl extends AbstractDaoImpl<Long, Result> implements ResultDao {

    private final AnswerService answerService;

    public ResultDaoImpl(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    public List<Result> getResultByQuestionAnswerList(List<QuestionAnswerRequestDto> questionAnswerRequestDtoList) {
        List<Result> result = new ArrayList<>();
        for (var dto : questionAnswerRequestDtoList) {
            result.add(entityManager.createQuery(
                            "select r from Result r where r.question.id =:id " +
                                    "and r.countRightAnswer = :countRightAnswer", Result.class)
                    .setParameter("countRightAnswer", answerService.countRightAnswersByQuestionAnswerRequestDto(dto))
                    .setParameter("id", dto.questionId())
                    .getSingleResult());
        }
        return result;
    }

}