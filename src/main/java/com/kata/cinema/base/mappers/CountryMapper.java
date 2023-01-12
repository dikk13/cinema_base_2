package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.response.CountryResponseDto;
import com.kata.cinema.base.models.Country;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryResponseDto toDto(Country country);
}
