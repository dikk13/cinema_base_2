package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.dto.MovieResponseDto;

import java.util.List;
import java.util.Map;

public interface MovieResponseDtoDao extends PaginationDtoDao<MovieResponseDto>{
    Map<Long, List<String>> getGenresMap(String moviesId);

    Map<Long, List<String>> getProducersMap(String moviesId);

    Map<Long, List<String>> getActorsMap(String moviesId);
}