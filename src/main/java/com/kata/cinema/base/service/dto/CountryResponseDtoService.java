package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.CountryResponseDto;

import java.util.List;

public interface CountryResponseDtoService {

    List<CountryResponseDto> getAllCountryResponseDto();
    List<CountryResponseDto> getListCountryResponseDtoByName(String filterPattern);
}
