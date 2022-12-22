package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.RedactorComment;
import com.kata.cinema.base.models.Review;

public interface ReviewService extends AbstractService<Long, Review> {

    Review getReviewById(Long newsId);

    void changeModerateFlag(Long id, RedactorComment redactorComment);

}
