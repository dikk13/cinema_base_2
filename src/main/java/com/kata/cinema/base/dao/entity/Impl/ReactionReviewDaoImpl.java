package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.ReactionReviewDao;
import com.kata.cinema.base.models.ReactionReview;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.Optional;

import static com.kata.cinema.base.dao.util.JpaResultHelper.jpaResultHelper;

@Repository
public class ReactionReviewDaoImpl extends AbstractDaoImpl<Long, ReactionReview> implements ReactionReviewDao{
    @Override
    public Optional<ReactionReview> getReactionReviewByUserIdAndReviewId(Long userId, Long reviewId) {
        return jpaResultHelper(entityManager.createQuery("select rr from ReactionReview rr " +
                        "where rr.user.id = :userId and rr.review.id = :reviewId", ReactionReview.class)
                .setParameter("userId", userId)
                .setParameter("reviewId", reviewId));
    }

}
