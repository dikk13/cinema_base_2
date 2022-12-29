package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.RatingComment;
import com.kata.cinema.base.models.enums.TypeRating;

public interface RatingCommentService extends AbstractService<Long, RatingComment> {
    void rateComment(Long userId, Long commentId, TypeRating rating);

}
