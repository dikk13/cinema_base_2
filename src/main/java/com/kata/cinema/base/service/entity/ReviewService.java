package com.kata.cinema.base.service.entity;


import com.kata.cinema.base.models.RedactorComment;
import com.kata.cinema.base.models.Review;

import java.util.List;


public interface ReviewService extends AbstractService<Long, Review> {

    Review getReviewByNewsId(Long newsId);

    void changeModerateFlag(Long id, RedactorComment redactorComment);


   Review getReviewById(Long id);
}
