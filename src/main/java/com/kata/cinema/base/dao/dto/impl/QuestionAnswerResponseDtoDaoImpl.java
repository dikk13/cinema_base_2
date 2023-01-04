package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.QuestionAnswerResponseDtoDao;
import com.kata.cinema.base.dto.response.AnswerResponseDto;
import com.kata.cinema.base.dto.response.QuestionAnswerResponseDto;
import com.kata.cinema.base.models.Answer;
import com.kata.cinema.base.models.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class QuestionAnswerResponseDtoDaoImpl implements QuestionAnswerResponseDtoDao {
    private final EntityManager entityManager;

    public QuestionAnswerResponseDtoDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public QuestionAnswerResponseDto getQuestionAnswerResponseDtoById(Long id) {
        QuestionAnswerResponseDto questionAnswerResponseDto = (QuestionAnswerResponseDto) entityManager
                .createQuery("select new com.kata.cinema.base.dto.response.QuestionAnswerResponseDto(" +
                        "q.id, q.position, q.question)" +
                        "from Question q where q.id = :id")
                .setParameter("id", id)
                .getSingleResult();
        questionAnswerResponseDto.setAnswers(getAnswerResponseDtoListById(id));
        return questionAnswerResponseDto;
    }

    @Override
    public List<AnswerResponseDto> getAnswerResponseDtoListById(Long id) {
        return getAnswerListById(id).stream()
                .map(answer -> new AnswerResponseDto(answer.getId(), answer.getAnswer()))
                .collect(Collectors.toList());
    }
    private List<Answer> getAnswerListById(Long id) {
        return entityManager.createQuery("select q.answers from Question q where q.id = :id")
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public Boolean isRightAnswer(Long questionId, Long answerId) {
        return (Boolean) entityManager.createQuery("select a.isRight from Answer a where a.id = :id")
                .setParameter("id", answerId).getSingleResult();
    }
}
