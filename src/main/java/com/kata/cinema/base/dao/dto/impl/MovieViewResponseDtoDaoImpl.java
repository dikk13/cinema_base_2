package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.MovieViewResponseDtoDao;
import com.kata.cinema.base.dto.response.MovieViewResponseDto;
import com.kata.cinema.base.models.enums.TypeContent;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.Optional;

import static com.kata.cinema.base.dao.util.JpaResultHelper.jpaResultHelper;


@Repository
public class MovieViewResponseDtoDaoImpl implements MovieViewResponseDtoDao {

    private final EntityManager entityManager;

    public MovieViewResponseDtoDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<MovieViewResponseDto> getMovieViewResponseDtoByMovieId(Long movieId) {
        return jpaResultHelper(entityManager.createQuery("" +
                        "select new com.kata.cinema.base.dto.response.MovieViewResponseDto(" +
                        "m.id, " +
                        "m.name, " +
                        "m.originalName, " +
                        "m.countries, " +
                        "m.dateRelease," +
                        "m.rars, " +
                        "m.mpaa, " +
                        "m.description, " +
                        "c.contentUrl, " +
                        "avg(s.score), " +
                        "count(s.score)) from Movie m join m.scores s join m.contents c where m.id = :movieId " +
                        "and c.type = :type group by m.id, c.contentUrl", MovieViewResponseDto.class)
                .setParameter("movieId", movieId)
                .setParameter("type", TypeContent.PREVIEW));
    }
}
