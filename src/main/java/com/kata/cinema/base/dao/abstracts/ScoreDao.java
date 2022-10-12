package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.models.Score;

import java.util.List;

public interface ScoreDao extends AbstractDao<Long, Score> {
    List<Score> getScoreListByMovieId (Long id);
}
