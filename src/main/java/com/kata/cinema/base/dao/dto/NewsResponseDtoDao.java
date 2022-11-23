package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.NewsResponseDto;

import java.util.List;

public interface NewsResponseDtoDao extends PaginationDtoDao<NewsResponseDto> {
    List<NewsResponseDto> getNewsResponseDtoByMovieId(Long movieId);
}
