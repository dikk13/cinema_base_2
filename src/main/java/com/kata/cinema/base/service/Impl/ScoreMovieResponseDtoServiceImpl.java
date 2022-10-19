package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.ScoreMovieResponseDtoDao;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.Score;
import com.kata.cinema.base.service.abstracts.MovieService;
import com.kata.cinema.base.service.abstracts.ScoreMovieResponseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreMovieResponseDtoServiceImpl extends AbstractServiceImpl<Long, Score> implements ScoreMovieResponseDtoService {

    private final MovieService movieService;

    @Autowired
    public ScoreMovieResponseDtoServiceImpl(ScoreMovieResponseDtoDao scoreMovieResponseDtoDao, MovieService movieService) {
        super(scoreMovieResponseDtoDao);
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
