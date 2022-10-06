package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dao.abstracts.MovieDao;
import com.kata.cinema.base.dto.search.SearchMovieDto;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.service.abstracts.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieServiceImpl extends AbstractServiceImpl<Long, Movie> implements MovieService {

    private final MovieDao movieDao;

    @Autowired
    protected MovieServiceImpl(AbstractDao<Long, Movie> abstractDao, MovieDao movieDao) {
        super(abstractDao);
        this.movieDao = movieDao;
    }

    @Override
    public List<SearchMovieDto> titleMovie(String name) {
        return movieDao.titleMovie(name);
    }
}
