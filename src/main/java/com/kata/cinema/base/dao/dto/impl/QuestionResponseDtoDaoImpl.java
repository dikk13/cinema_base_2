package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.QuestionResponseDtoDao;
import com.kata.cinema.base.dto.response.QuestionResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class QuestionResponseDtoDaoImpl implements QuestionResponseDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<QuestionResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.response.QuestionResponseDto" +
                        "(q.id, q.position, q.question)" + "from Question q", QuestionResponseDto.class)
                .setFirstResult((currentPage - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList();
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return entityManager.createQuery("select count (q) from Question q", Long.class).getSingleResult();
    }
}
