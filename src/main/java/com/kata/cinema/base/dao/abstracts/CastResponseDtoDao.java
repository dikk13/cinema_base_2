package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.dto.CastResponseDto;

import java.util.List;

public interface CastResponseDtoDao {
    List<CastResponseDto> getCastResponseDtoListByMovieId(Long id);
}
