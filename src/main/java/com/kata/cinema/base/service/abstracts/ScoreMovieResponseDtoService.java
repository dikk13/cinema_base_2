package com.kata.cinema.base.service.abstracts;


import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.Score;

public interface ScoreMovieResponseDtoService {

    Movie findMovie(Long movieId);
}
