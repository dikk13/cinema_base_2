package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dao.abstracts.ScoreDao;
import com.kata.cinema.base.models.Score;
import com.kata.cinema.base.service.abstracts.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreServiceImpl extends AbstractServiceImpl<Long, Score> implements ScoreService {

    private final ScoreDao scoreDao;

    protected ScoreServiceImpl(AbstractDao<Long, Score> abstractDao, ScoreDao scoreDao) {
        super(abstractDao);
        this.scoreDao = scoreDao;
    }

    @Override
    public Score getScoreByUserAndMovieId(Long userId, Long movieId) {
        return scoreDao.getScoreByUserAndMovieId(userId, movieId);
    }
}
