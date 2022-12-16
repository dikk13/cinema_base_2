package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.AwardResponseDto;

import java.util.List;

public interface MoviesAwardResponseDtoDao {

    List<AwardResponseDto> getMoviesAwards();

}
