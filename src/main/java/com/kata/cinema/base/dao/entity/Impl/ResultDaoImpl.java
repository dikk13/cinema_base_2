package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.ResultDao;
import com.kata.cinema.base.dto.request.QuestionAnswerRequestDto;
import com.kata.cinema.base.models.Result;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResultDaoImpl extends AbstractDaoImpl<Long, Result> implements ResultDao {

    @Override
    public Result getResultByQuestionAnswerList(List<QuestionAnswerRequestDto> questionAnswerRequestDtoList) {
        Result result = new Result();
        int actualCountRightAnswer = 0;
        int countRightAnswerByDto = 0;
        for (QuestionAnswerRequestDto requestDto : questionAnswerRequestDtoList) {
            actualCountRightAnswer += entityManager.createQuery(
                            "select count(a) from Answer a where a.isRight =:isRight " +
                                    "and a.question.id =:id", Integer.class)
                    .setParameter("isRight", true)
                    .setParameter("id", requestDto.getQuestionId())
                    .getSingleResult();
            countRightAnswerByDto += requestDto.getAnswerId().size();
        }
        result.setCountRightAnswer(actualCountRightAnswer);
        if (actualCountRightAnswer == countRightAnswerByDto) {
            result.setResult("Количество правильных ответов совпадает");
        } else {
            result.setResult("Количество правильных ответов не совпадает");
        }
        return result;
    }

}