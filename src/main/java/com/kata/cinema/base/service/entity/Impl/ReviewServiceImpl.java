package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.ReviewDao;
import com.kata.cinema.base.models.RedactorComment;
import com.kata.cinema.base.models.Review;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.ReviewService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.kata.cinema.base.models.enums.RedactorStatus.RESOLVED;

@Service
public class ReviewServiceImpl extends AbstractServiceImpl<Long, Review> implements ReviewService {
    private final ReviewDao reviewDao;

    protected ReviewServiceImpl(ReviewDao reviewDao) {
        super(reviewDao);
        this.reviewDao = reviewDao;
    }


    @Override
    public Review getReviewById(Long reviewId) {
        Optional<Review> review = reviewDao.getById(reviewId);
        if (review.isPresent()) {
            return review.get();
        } else {
            throw new NullPointerException("Ревью не найдено");
        }
    }

    @Override
    public void changeModerateFlag(Long id, RedactorComment redactorComment) {
        if (redactorComment.getStatus() == RESOLVED) {
            Review review = getReviewById(id);
            review.setIsModerate(true);
            update(review);
        }
    }

}
