package com.kata.cinema.base.service.entity.Impl;


import com.kata.cinema.base.dao.entity.MovieDao;
import com.kata.cinema.base.dto.SearchMovieDto;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieServiceImpl extends AbstractServiceImpl<Long, Movie> implements MovieService {

    private final MovieDao movieDao;

    @Autowired
    protected MovieServiceImpl(MovieDao movieDao) {
        super(movieDao);
        this.movieDao = movieDao;
    }

    @Override
    public List<SearchMovieDto> titleMovie(String name) {
        return movieDao.titleMovie(name);
    }

}
