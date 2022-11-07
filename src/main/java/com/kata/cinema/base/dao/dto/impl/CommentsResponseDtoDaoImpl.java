package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.CommentsResponseDtoDao;
import com.kata.cinema.base.dto.response.CommentsResponseDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

public class CommentsResponseDtoDaoImpl implements CommentsResponseDtoDao {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public CommentsResponseDto getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
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
        return entityManager.createQuery("select count (r) from Review r where r.movie.id = :movieId", Long.class)
                .setParameter("movieId", parameters.get("movieId"))
                .getSingleResult();
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return null;
    }


    @Override
    public List<CommentsResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return null;
    }
}
