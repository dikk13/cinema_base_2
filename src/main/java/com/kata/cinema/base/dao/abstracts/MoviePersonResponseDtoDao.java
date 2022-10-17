package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.dto.MoviePersonResponseDto;

import java.util.List;

public interface MoviePersonResponseDtoDao {
    List<MoviePersonResponseDto> getMoviePersonResponseDtoListByMovieId(Long id);

}
