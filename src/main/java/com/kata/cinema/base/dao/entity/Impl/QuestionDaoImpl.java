package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.QuestionDao;
import com.kata.cinema.base.models.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDaoImpl extends AbstractDaoImpl<Long, Question> implements QuestionDao {

    @Override
    public void deleteQuestionWithAnswersAndResultsById(Long questionId) {
        entityManager.createQuery("delete from Question q where q.id =: id")
                .setParameter("id", questionId)
                .executeUpdate();
    }

    @Override
    public List<Question> getAllQuestionsByNewsId(Long id) {
        return entityManager.createQuery("select q from Question q where q.news.id =:id", Question.class)
                .setParameter("id", id)
                .getResultList();
    }
}
