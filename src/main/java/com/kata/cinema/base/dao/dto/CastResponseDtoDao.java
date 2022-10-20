package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.CastResponseDto;

import java.util.List;

public interface CastResponseDtoDao {
    List<CastResponseDto> getCastResponseDtoListByMovieId(Long id);
}
