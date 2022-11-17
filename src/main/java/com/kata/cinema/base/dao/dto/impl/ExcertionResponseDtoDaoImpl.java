package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.ExcertionResponseDtoDao;
import com.kata.cinema.base.dto.response.ExcertionResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

import static com.kata.cinema.base.dao.util.JpaResultHelper.jpaResultHelper;

@Repository
public class ExcertionResponseDtoDaoImpl implements ExcertionResponseDtoDao {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<ExcertionResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return entityManager.createQuery(" SELECT NEW com.kata.cinema.base.dto.response.ExcertionResponseDto" +
                                "(e.id, e.description)" +
                                "FROM Excertion e " +
                                "WHERE e.person.id = :personId OR e.movie.id = :movieId"
                        , ExcertionResponseDto.class)
                .setParameter("personId", parameters.get("personId"))
                .setParameter("movieId", parameters.get("movieId"))
                .setFirstResult((currentPage - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList();
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return jpaResultHelper(entityManager.createQuery("SELECT COUNT (e) FROM Excertion e " +
                        "WHERE e.person.id = :personId OR e.movie.id = :movieId", Long.class)
                .setParameter("personId", parameters.get("personId"))
                .setParameter("movieId", parameters.get("movieId"))).orElse(null);
    }

}
