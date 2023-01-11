package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.CountryResponseDtoDao;
import com.kata.cinema.base.dto.response.CountryResponseDto;
import com.kata.cinema.base.service.dto.CountryResponseDtoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryResponseDtoServiceImpl implements CountryResponseDtoService {
    private final CountryResponseDtoDao countryResponseDtoDao;

    public CountryResponseDtoServiceImpl(CountryResponseDtoDao countryResponseDtoDao) {
        this.countryResponseDtoDao = countryResponseDtoDao;
    }

    @Override
    public List<CountryResponseDto> getAllCountryResponseDto() {
        return countryResponseDtoDao.getAllCountryResponseDto();
    }

    @Override
    public List<CountryResponseDto> getListCountryResponseDtoByName(String filterPattern) {
        return countryResponseDtoDao.getListCountryResponseDtoByName(filterPattern);
    }
}
