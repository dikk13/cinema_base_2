package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.dto.MoviePersonResponseDto;

import java.util.List;

public interface MoviePersonResponseDtoService {
    List<MoviePersonResponseDto> getMoviePersonResponseDtoListByMovieId(Long id);
}
