package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.dto.SearchMovieDto;
import com.kata.cinema.base.models.Movie;

import java.util.List;

public interface MovieDao extends AbstractDao<Long, Movie> {

    List<SearchMovieDto> titleMovie(String name);

}
