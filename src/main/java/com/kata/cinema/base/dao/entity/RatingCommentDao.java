package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.RatingComment;

import java.util.Optional;

public interface RatingCommentDao extends AbstractDao<Long, RatingComment> {

    Optional<RatingComment> getByUserIdCommentId(Long userId, Long commentId);
}
