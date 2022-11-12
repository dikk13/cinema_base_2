package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.ReactionReviewDao;
import com.kata.cinema.base.models.ReactionReview;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.ReactionReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReactionReviewServiceImpl extends AbstractServiceImpl<Long, ReactionReview> implements ReactionReviewService {

    @Autowired
    protected ReactionReviewServiceImpl(ReactionReviewDao reactionReviewDao) {
        super(reactionReviewDao);
    }
}
