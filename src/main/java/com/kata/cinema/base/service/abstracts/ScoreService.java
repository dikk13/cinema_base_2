package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.models.Score;

import java.util.List;

public interface ScoreService extends AbstractService<Long, Score> {
    List<Score> getScoreListByMovieId (Long id);
}
