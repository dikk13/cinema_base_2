package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.MoviePersonResponseDto;

import java.util.List;

public interface MoviePersonResponseDtoDao {
    List<MoviePersonResponseDto> getMoviePersonResponseDtoListByMovieId(Long id);

}
