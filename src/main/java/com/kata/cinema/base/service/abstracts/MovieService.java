package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.dto.search.SearchMovieDto;
import com.kata.cinema.base.models.Movie;

import java.util.List;

public interface MovieService extends AbstractService<Long, Movie> {

    public List<SearchMovieDto> titleMovie();
}
