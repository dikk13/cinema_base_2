package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.ReviewDao;
import com.kata.cinema.base.models.Review;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDaoImpl extends AbstractDaoImpl<Long, Review> implements ReviewDao {
}
