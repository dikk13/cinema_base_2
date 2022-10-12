package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.dto.search.SearchMovieDto;
import com.kata.cinema.base.models.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService extends AbstractService<Long, Movie> {

    List<SearchMovieDto> titleMovie(String name);

    Optional<Movie> getMovieWithMoviePersonsWithProfessionsAndPersonsByMovieId(Long id);

//    String getPreviewUrlByMovieId(Long id);
}
