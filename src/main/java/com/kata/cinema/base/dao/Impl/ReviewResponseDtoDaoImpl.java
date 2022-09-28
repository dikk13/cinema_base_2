package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.ReviewResponseDtoDao;

import com.kata.cinema.base.dto.ReviewResponseDto;
import com.kata.cinema.base.enums.ReviewSortType;
import com.kata.cinema.base.enums.TypeReview;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Map;


@Repository
public class ReviewResponseDtoDaoImpl implements ReviewResponseDtoDao {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<ReviewResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return  entityManager.createQuery(" SELECT new com.kata.cinema.base.dto.ReviewResponseDto" +
                "(r.id,r.typeReview,r.title,r.description," +
                "concat(r.user.first_name,' ', r.user.last_name)," +
                " r.date) " +
                "FROM Review r " +
                " where r.movie.id = :movieId" + sortingByTypeReview((TypeReview) parameters.get("typeReview")) + sortingByReviewSortType((ReviewSortType) parameters.get("reviewSortType"))
                ,ReviewResponseDto.class)
                        .setParameter("movieId",parameters.get("movieId"))
                                .setFirstResult((currentPage-1)*itemsOnPage)
                                        .setMaxResults(itemsOnPage)
                                                .getResultList();


    }


    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        Long query=entityManager.createQuery("select count (*) from Review r where r.movie.id = :movieId"+
                        sortingByTypeReview ((TypeReview) parameters.get("typeReview")), Long.class)
                .setParameter("movieId",parameters.get("movieId") )
                .getSingleResult();
        return query;
    }

    //Help me please
    @Override
    public String sortingByTypeReview(TypeReview typeReview) {
        if (typeReview != null) {
            switch (typeReview) {
                case POSITIVE:
                    return "where r.typeReview = 'POSITIVE'";
                case NEUTRAL:
                    return "where r.typeReview = 'NEUTRAL'";
                case NEGATIVE:
                    return "where r.typeReview = 'NEGATIVE'";
            }
        }
        return "";
    }

    @Override
    public String sortingByReviewSortType(ReviewSortType reviewSortType) {
        if (reviewSortType != null) {
            switch (reviewSortType) {
                case DATE_ASC -> {
                    return "ORDER BY r.date ASC";
                }
                case DATE_DESC -> {
                    return "ORDER BY r.date DESC";
                }
            }

        }
        return "";
    }
}
