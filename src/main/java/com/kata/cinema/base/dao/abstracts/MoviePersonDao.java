package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.models.MoviePerson;

import java.util.List;

public interface MoviePersonDao extends AbstractDao<Long, MoviePerson> {
    List<MoviePerson> getMoviePersonListByMovieId(Long movieId);
}
