package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.ScoreMovieDao;
import com.kata.cinema.base.dto.ScoreMovieResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class ScoreMovieDaoImpl implements ScoreMovieDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ScoreMovieResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return entityManager.createQuery("SELECT new com.kata.cinema.base.dto.ScoreMovieResponseDto" +
                "(s.id + s.score + s.date)" + "FROM Score s", ScoreMovieResponseDto.class)
                .setFirstResult((currentPage - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList();
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return entityManager.createQuery("select count (s) from Score s", Long.class)
                .getSingleResult();
    }
}
