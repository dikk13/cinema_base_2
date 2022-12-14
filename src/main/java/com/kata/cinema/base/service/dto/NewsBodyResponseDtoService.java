package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.NewsBodyResponseDto;

import java.util.Optional;

public interface NewsBodyResponseDtoService {
    Optional<NewsBodyResponseDto> getNewsBodyResponseDtoById(Long id);

}
