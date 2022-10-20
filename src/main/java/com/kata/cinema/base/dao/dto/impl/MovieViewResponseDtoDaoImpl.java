package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.MovieViewResponseDtoDao;
import com.kata.cinema.base.dto.response.MovieViewResponseDto;
import com.kata.cinema.base.models.enums.ContentType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class MovieViewResponseDtoDaoImpl implements MovieViewResponseDtoDao {

    private final EntityManager entityManager;

    public MovieViewResponseDtoDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public MovieViewResponseDto getMovieViewResponseDtoByMovieId(Long movieId) {
        return (MovieViewResponseDto) entityManager.createQuery("" +
                        "select new com.kata.cinema.base.dto.response.MovieViewResponseDto(" +
                        "m.id, " +
                        "m.name, " +
                        "m.originalName, " +
                        "m.countries, " +
                        "m.dateRelease," +
                        "m.rars, " +
                        "m.mpaa, " +
                        "m.description, " +
                        "c.content_url, " +
                        "avg(s.score), " +
                        "count(s.score)) from Movie m join m.scores s join m.contents c where m.id = :movieId " +
                        "and c.type = :type group by m.id, c.content_url")
                .setParameter("movieId", movieId)
                .setParameter("type", ContentType.PREVIEW)
                .getSingleResult();
    }
}
