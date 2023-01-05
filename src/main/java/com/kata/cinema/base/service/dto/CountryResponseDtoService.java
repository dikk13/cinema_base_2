package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.CountryResponseDto;
import com.kata.cinema.base.dto.response.ExcertionResponseDto;

public interface CountryResponseDtoService extends PaginationDtoService<ExcertionResponseDto>{

    CountryResponseDto getCountryResponseDtoByMovieId(Long id);
}
