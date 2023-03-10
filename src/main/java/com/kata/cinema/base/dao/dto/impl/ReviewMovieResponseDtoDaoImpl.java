package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.ReviewMovieResponseDtoDao;
import com.kata.cinema.base.dto.response.ReviewMovieResponseDto;
import com.kata.cinema.base.models.enums.TypeReview;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class ReviewMovieResponseDtoDaoImpl implements ReviewMovieResponseDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ReviewMovieResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return entityManager.createQuery("SELECT new com.kata.cinema.base.dto.response.ReviewMovieResponseDto" +
                                "(" +
                                "(count (r))," +
                                "(select count (r) from Review r where r.typeReview = :positive and r.movie.id = :movieId)," +
                                "(select count (r) from Review r where r.typeReview = :negative and r.movie.id = :movieId)," +
                                "(select count (r) from Review r where r.typeReview = :neutral and r.movie.id = :movieId)" +
                                ") " +
                                "FROM Review r " +
                                "where r.movie.id = :movieId AND (r.typeReview = :typeReview or :typeReview is null)"
                        , ReviewMovieResponseDto.class)
                .setParameter("positive", TypeReview.POSITIVE)
                .setParameter("negative", TypeReview.NEGATIVE)
                .setParameter("neutral", TypeReview.NEUTRAL)
                .setParameter("movieId", parameters.get("movieId"))
                .setParameter("typeReview", parameters.get("typeReview"))
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
}
