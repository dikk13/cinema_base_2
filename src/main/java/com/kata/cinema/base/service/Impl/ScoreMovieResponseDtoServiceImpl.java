package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.ScoreMovieResponseDtoDao;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.Score;
import com.kata.cinema.base.service.abstracts.ScoreMovieResponseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ScoreMovieResponseDtoServiceImpl extends AbstractServiceImpl<Long, Score> implements ScoreMovieResponseDtoService {

    private final ScoreMovieResponseDtoDao scoreMovieResponseDtoDao;

    @Autowired
    public ScoreMovieResponseDtoServiceImpl(ScoreMovieResponseDtoDao scoreMovieResponseDtoDao) {
        super(scoreMovieResponseDtoDao);
        this.scoreMovieResponseDtoDao = scoreMovieResponseDtoDao;
    }

}
