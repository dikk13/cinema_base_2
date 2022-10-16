package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.dto.CastResponseDto;
import com.kata.cinema.base.dto.MoviePersonResponseDto;

import java.util.List;

public interface CastResponseDtoService {
    List<CastResponseDto> getCastResponseDtoListByMovieId(Long id);

    List<CastResponseDto> getCastResponseDtoWithMoviePersonDtosListByMovieId(Long id, List<MoviePersonResponseDto> persons);
}
