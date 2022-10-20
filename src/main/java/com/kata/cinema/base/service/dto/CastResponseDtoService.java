package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.CastResponseDto;
import com.kata.cinema.base.dto.response.MoviePersonResponseDto;

import java.util.List;

public interface CastResponseDtoService {
    List<CastResponseDto> getCastResponseDtoListByMovieId(Long id);

    List<CastResponseDto> getCastResponseDtoWithMoviePersonDtosListByMovieId(Long id, List<MoviePersonResponseDto> persons);
}
