package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.dto.MovieReleaseAndTimeDto;
import com.kata.cinema.base.dto.SearchMovieDto;
import com.kata.cinema.base.dto.request.MovieRequestDto;
import com.kata.cinema.base.models.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieService extends AbstractService<Long, Movie> {

    List<SearchMovieDto> titleMovie(String name);

    List<MovieReleaseAndTimeDto> movieRelease(LocalDate date);

    void updateById(Long id, MovieRequestDto movieRequestDto);

    Movie getMovieById(Long movieId);

    void addCountriesToMovie(Long movieId, List<Long> countryId);
}
