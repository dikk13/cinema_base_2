package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.NewsResponseDto;

import java.util.List;

public interface NewsResponseDtoService extends PaginationDtoService<NewsResponseDto> {
    List<NewsResponseDto> getNewsResponseDtoByMovieId(Long movieId);
}
