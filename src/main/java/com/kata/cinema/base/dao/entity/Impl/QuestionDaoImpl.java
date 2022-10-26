package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.Impl.AbstractDaoImpl;
import com.kata.cinema.base.dao.entity.QuestionDao;
import com.kata.cinema.base.models.Question;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class QuestionDaoImpl extends AbstractDaoImpl<Long, Question> implements QuestionDao {

    @Override
    @Transactional
    public void deleteQuestionWithAnswersAndResultsById(Long questionId ) {
        entityManager.createQuery("delete from Question q where q.id =: id")
                .setParameter("id", questionId)
                .executeUpdate();

    }
}
