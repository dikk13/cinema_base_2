package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.ScoreMovieDao;
import com.kata.cinema.base.dto.ScoreMovieResponseDto;
import com.kata.cinema.base.service.abstracts.ScoreMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreMovieServiceImpl extends PaginationDtoServiceImpl<ScoreMovieResponseDto> implements ScoreMovieService{

    private final ScoreMovieDao scoreMovieDao;

    @Autowired
    public ScoreMovieServiceImpl(ScoreMovieDao scoreMovieDao) {
        super(scoreMovieDao);
        this.scoreMovieDao = scoreMovieDao;
    }
}
