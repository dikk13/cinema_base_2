package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.Genre;

import java.util.List;
import java.util.Map;


public interface GenreService  extends AbstractService<Long, Genre>  {

    String getGenresOfMovieByMovieId(Long id);

    Map<Long, List<String>> getGenresMap(String moviesId);
}
