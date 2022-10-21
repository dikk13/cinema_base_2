package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.models.Score;

public interface ScoreDao extends AbstractDao<Long, Score> {
    Integer getScoreByMovieIdAndUserId(Long movieId, Long userId);
}
