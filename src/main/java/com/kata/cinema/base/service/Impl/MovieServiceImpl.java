package com.kata.cinema.base.service.Impl;


import com.kata.cinema.base.dao.abstracts.MovieDao;
import com.kata.cinema.base.dto.search.SearchMovieDto;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.service.abstracts.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Movie> getMovieWithMoviePersonsWithProfessionsAndPersonsByMovieId(Long id) {
        return movieDao.getMovieWithMoviePersonsWithProfessionsAndPersonsByMovieId(id);
    }

//    public String getPreviewUrlByMovieId(Long id) {
//        return movieDao.getPreviewUrlByMovieId(id);
//    }
}
