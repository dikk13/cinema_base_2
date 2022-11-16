package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.ReactionReview;

import java.util.Optional;

public interface ReactionReviewDao extends AbstractDao<Long, ReactionReview>{
    Optional<ReactionReview> getReactionReviewByUserIdAndReviewId(Long userId, Long reviewId);
}
