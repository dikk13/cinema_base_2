package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.ResultDao;
import com.kata.cinema.base.dto.request.QuestionAnswerRequestDto;
import com.kata.cinema.base.models.Result;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ResultDaoImpl extends AbstractDaoImpl<Long, Result> implements ResultDao {

    @Override
    public List<Result> getResultByQuestionAnswerList(List<QuestionAnswerRequestDto> questionAnswerRequestDtoList) {
        List<Result> result = new ArrayList<>();
        for (var dto : questionAnswerRequestDtoList) {
            Integer rightAnswers = null; // считаем правильные ответы на вопрос
            for (var answerId : dto.answerId()) {
                rightAnswers += entityManager.createQuery("select count(a) " +
                                "from Answer a where a.id = :id and a.isRight =:isRight", Integer.class)
                        .setParameter("isRight", true)
                        .setParameter("id", answerId)
                        .getSingleResult();
            }
            result.add(entityManager.createQuery(
                            "select r from Result r where r.question.id =:id " +
                                    "and r.countRightAnswer = :countRightAnswer", Result.class)
                    .setParameter("countRightAnswer", rightAnswers)
                    .setParameter("id", dto.questionId())
                    .getSingleResult());
        }
        return result;
    }

}