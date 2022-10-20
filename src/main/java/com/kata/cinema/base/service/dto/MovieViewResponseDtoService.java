package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.MovieViewResponseDto;

public interface MovieViewResponseDtoService {
    MovieViewResponseDto getMovieViewResponseDtoByMovieId(Long id);
}
