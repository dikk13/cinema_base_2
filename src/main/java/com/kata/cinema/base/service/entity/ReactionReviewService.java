package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.ReactionReview;

import java.util.Optional;

public interface ReactionReviewService extends AbstractService<Long, ReactionReview> {
    Optional<ReactionReview> getReactionReviewByUserIdAndReviewId(Long userId, Long reviewId);
}
