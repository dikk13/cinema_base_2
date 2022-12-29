package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.RatingCommentDao;
import com.kata.cinema.base.models.RatingComment;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class RatingCommentDaoImpl extends AbstractDaoImpl<Long, RatingComment> implements RatingCommentDao {
    @Override
    public Optional<RatingComment> getByUserIdCommentId(Long userId, Long commentId) {
        RatingComment ratingComment = (RatingComment) entityManager.createQuery("select rc from RatingComment rc " +
                "where rc.user.id = :userId and rc.comment.id = :commentId")
                .setParameter("userId", userId)
                .setParameter("commentId", commentId)
                .getSingleResult();
        return Optional.of(ratingComment);
    }
}
