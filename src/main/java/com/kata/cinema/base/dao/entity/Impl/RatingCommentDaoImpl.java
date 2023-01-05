package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.RatingCommentDao;
import com.kata.cinema.base.dao.util.JpaResultHelper;
import com.kata.cinema.base.models.RatingComment;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Optional;
@Repository
public class RatingCommentDaoImpl extends AbstractDaoImpl<Long, RatingComment> implements RatingCommentDao {
    @Override
    public Optional<RatingComment> getByUserIdCommentId(Long userId, Long commentId) {
        TypedQuery<RatingComment> typedQuery = entityManager.createQuery("select rc from RatingComment rc " +
                "where rc.user.id = :userId and rc.comments.id = :commentId", RatingComment.class)
                .setParameter("userId", userId)
                .setParameter("commentId", commentId);
        return JpaResultHelper.jpaResultHelper(typedQuery);
    }
}
