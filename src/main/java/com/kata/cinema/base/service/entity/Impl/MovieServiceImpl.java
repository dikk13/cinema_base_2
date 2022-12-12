package com.kata.cinema.base.service.entity.Impl;


import com.kata.cinema.base.dao.entity.MovieDao;
import com.kata.cinema.base.dto.SearchMovieDto;
import com.kata.cinema.base.dto.request.MovieRequestDto;
import com.kata.cinema.base.mappers.MovieMapper;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.GenreService;
import com.kata.cinema.base.service.entity.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieServiceImpl extends AbstractServiceImpl<Long, Movie> implements MovieService {

    private final MovieDao movieDao;
    private final GenreService genreService;
    private final MovieMapper movieMapper;

    @Autowired
    protected MovieServiceImpl(MovieDao movieDao, GenreService genreService, MovieMapper movieMapper) {
        super(movieDao);
        this.movieDao = movieDao;
        this.genreService = genreService;
        this.movieMapper = movieMapper;
    }

    @Override
    public List<SearchMovieDto> titleMovie(String name) {
        return movieDao.titleMovie(name);
    }

    @Override
    @Transactional
    public void updateById(Long id, MovieRequestDto movie) {
        Optional<Movie> optionalMovie = getById(id);
        if (optionalMovie.isPresent()) {
           Movie movieToUpdate = movieMapper.toMovie(movie);
           movieDao.update(movieToUpdate);
        }
    }

    @Override
    public Movie getMovieById(Long movieId) {
        Optional<Movie> movie = movieDao.getById(movieId);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            throw new NullPointerException("Фильм не найден");
        }
    }
}
