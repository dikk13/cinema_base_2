package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dao.entity.ScoreDao;
import com.kata.cinema.base.models.Score;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.ScoreService;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl extends AbstractServiceImpl<Long, Score> implements ScoreService {

    private final ScoreDao scoreDao;

    protected ScoreServiceImpl(AbstractDao<Long, Score> abstractDao, ScoreDao scoreDao) {
        super(abstractDao);
        this.scoreDao = scoreDao;
    }

    @Override
    public Integer getScoreByMovieIdAndUserId(Long movieId, Long userId) {
        return scoreDao.getScoreByMovieIdAndUserId(movieId, userId);
    }
}
