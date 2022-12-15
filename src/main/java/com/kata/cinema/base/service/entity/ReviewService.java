package com.kata.cinema.base.service.entity;


import com.kata.cinema.base.models.Review;

import java.util.List;


public interface ReviewService extends AbstractService<Long, Review> {

   Review getReviewById(Long id);
}
