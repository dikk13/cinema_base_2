package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.MovieViewResponseDto;

import java.util.Optional;

public interface MovieViewResponseDtoDao {
    Optional<MovieViewResponseDto> getMovieViewResponseDtoByMovieId(Long movieId);
}
