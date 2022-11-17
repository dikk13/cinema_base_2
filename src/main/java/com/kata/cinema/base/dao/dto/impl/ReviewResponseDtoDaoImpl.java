package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.ReviewResponseDtoDao;
import com.kata.cinema.base.dto.response.ReviewResponseDto;
import com.kata.cinema.base.models.enums.TypeRating;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

import static com.kata.cinema.base.dao.util.JpaResultHelper.jpaResultHelper;


@Repository
public class ReviewResponseDtoDaoImpl implements ReviewResponseDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ReviewResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return entityManager.createQuery(" SELECT new com.kata.cinema.base.dto.response.ReviewResponseDto" +
                                "(r.id,r.typeReview,r.title,r.description," +
                                "concat(r.user.first_name,' ', r.user.last_name)," +
                                "r.date," +
                                "(select count (rr) from ReactionReview rr where rr.rating = :like and rr.review.id = r.id)," +
                                "(select count (rr) from ReactionReview rr where rr.rating = :dislike and rr.review.id = r.id)) " +
                                "FROM Review r " +
                                "where r.movie.id = :movieId AND r.typeReview = :typeReview"
                        , ReviewResponseDto.class)
                .setParameter("like", TypeRating.LIKE)
                .setParameter("dislike", TypeRating.DISLIKE)
                .setParameter("movieId", parameters.get("movieId"))
                .setParameter("typeReview", parameters.get("typeReview"))
                .setFirstResult((currentPage - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList();
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return jpaResultHelper(entityManager.createQuery("select count (r) from Review r where r.movie.id = :movieId", Long.class)
                .setParameter("movieId", parameters.get("movieId"))).orElse(null);
    }
}
