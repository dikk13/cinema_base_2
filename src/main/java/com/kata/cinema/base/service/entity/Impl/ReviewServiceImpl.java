package com.kata.cinema.base.service.entity.Impl;
import com.kata.cinema.base.dao.entity.ReviewDao;
import com.kata.cinema.base.models.Review;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ReviewServiceImpl extends AbstractServiceImpl<Long, Review> implements ReviewService {
    private final ReviewDao reviewDao;

    @Autowired
    protected ReviewServiceImpl(ReviewDao reviewDao) {
        super(reviewDao);
        this.reviewDao = reviewDao;
    }

    public Review getReviewById(Long id){
        return reviewDao.getReviewById(id);
    }
}
