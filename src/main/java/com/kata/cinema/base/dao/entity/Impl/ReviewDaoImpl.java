package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.ReviewDao;
import com.kata.cinema.base.models.FolderMovie;
import com.kata.cinema.base.models.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDaoImpl extends AbstractDaoImpl<Long, Review> implements ReviewDao {
    public Review getReviewById(Long id) {
        return (Review) entityManager.createQuery("select r from Review r where r.id =: id", Review.class)
                .setParameter("id", id)
                .getResultList();
    }
}
