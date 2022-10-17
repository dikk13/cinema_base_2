package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.models.Score;

public interface ScoreService extends AbstractService<Long, Score> {
    Integer getScoreByMovieIdAndUserId(Long movieId, Long userId);
}
