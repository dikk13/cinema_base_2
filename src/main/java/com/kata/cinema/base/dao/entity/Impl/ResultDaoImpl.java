package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.ResultDao;
import com.kata.cinema.base.models.QuestionAnswer;
import com.kata.cinema.base.models.Result;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResultDaoImpl extends AbstractDaoImpl<Long, Result> implements ResultDao {

    @Override
    public Result getResultByQuestionAnswerList(List<QuestionAnswer> fromDTO) {
        Result result = new Result();
        int actualCountRightAnswer = 0;
        int countRightAnswerByDto = 0;
        for (QuestionAnswer questionAnswer : fromDTO) {
            actualCountRightAnswer += entityManager.createQuery(
                            "SELECT COUNT(a) FROM Answer a WHERE a.isRight =:isRight " +
                                    "AND a.question.id =:id", Integer.class)
                    .setParameter("isRight", true)
                    .setParameter("id", questionAnswer.getQuestion().getId())
                    .getSingleResult();
//            countRightAnswerByDto += questionAnswer.getAnswerId()
//                    .stream()
//                    .mapToInt(Long::intValue)
//                    .sum();
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