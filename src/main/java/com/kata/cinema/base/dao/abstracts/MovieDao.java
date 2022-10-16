package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.dto.search.SearchMovieDto;
import com.kata.cinema.base.models.Movie;

import java.util.List;

public interface MovieDao extends AbstractDao<Long, Movie> {

    List<SearchMovieDto> titleMovie(String name);

}
