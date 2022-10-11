package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.models.MoviePerson;

import java.util.List;

public interface MoviePersonService extends AbstractService<Long, MoviePerson> {
    List<MoviePerson> getMoviePersonListByMovieId(Long movieId);
}
