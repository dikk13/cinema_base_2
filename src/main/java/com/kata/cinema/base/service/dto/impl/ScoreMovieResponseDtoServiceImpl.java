package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.dto.ScoreMovieResponseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreMovieResponseDtoServiceImpl implements ScoreMovieResponseDtoService {

    private final MovieService movieService;

    @Autowired
    public ScoreMovieResponseDtoServiceImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public Movie findMovie(Long movieId){
        Optional<Movie> movie = movieService.getById(movieId);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            throw new NullPointerException("Фильм не найден");
        }
    }


}
