package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.CountryResponseDto;

import java.util.List;

public interface CountryResponseDtoDao {
    List<CountryResponseDto> getAllCountryResponseDto();
    List<CountryResponseDto> getListCountryResponseDtoByName(String filterPattern);
}
