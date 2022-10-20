package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.MovieViewResponseDto;

public interface MovieViewResponseDtoDao {
    MovieViewResponseDto getMovieViewResponseDtoByMovieId(Long movieId);
}
