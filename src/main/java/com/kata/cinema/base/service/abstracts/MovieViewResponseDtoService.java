package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.dto.MovieViewResponseDto;

public interface MovieViewResponseDtoService {
    MovieViewResponseDto getMovieViewResponseDtoByMovieId(Long id);
}
