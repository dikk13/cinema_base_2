package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.MoviePersonResponseDto;

import java.util.List;

public interface MoviePersonResponseDtoService {
    List<MoviePersonResponseDto> getMoviePersonResponseDtoListByMovieId(Long id);
}
