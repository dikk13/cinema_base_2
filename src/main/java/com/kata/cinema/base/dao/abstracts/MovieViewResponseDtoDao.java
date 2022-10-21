package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.dto.MovieViewResponseDto;

public interface MovieViewResponseDtoDao {
    MovieViewResponseDto getMovieViewResponseDtoByMovieId(Long movieId);
}
