package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.dto.SearchMovieDto;
import com.kata.cinema.base.models.Movie;

import java.util.List;

public interface MovieService extends AbstractService<Long, Movie> {

    List<SearchMovieDto> titleMovie(String name);

}
