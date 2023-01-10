package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.AnswerDao;
import com.kata.cinema.base.dto.request.QuestionAnswerRequestDto;
import com.kata.cinema.base.models.Answer;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerDaoImpl extends AbstractDaoImpl <Long, Answer> implements AnswerDao {

    @Override
    public Integer countRightAnswersByQuestionAnswerRequestDto(QuestionAnswerRequestDto requestDto) {
        return entityManager.createQuery("select count(a) " +
                        "from Answer a where a.id in :list and a.isRight =:isRight", Integer.class)
                .setParameter("isRight", true)
                .setParameter("list", requestDto.answerId())
                .getSingleResult();
    }

}
