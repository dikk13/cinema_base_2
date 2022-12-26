package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.Review;

import java.util.List;

public interface ReviewDao extends AbstractDao<Long, Review> {
    Review getReviewById(Long id);
}
