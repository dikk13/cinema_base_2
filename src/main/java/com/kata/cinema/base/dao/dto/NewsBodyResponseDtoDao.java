package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.NewsBodyResponseDto;

import java.util.Optional;

public interface NewsBodyResponseDtoDao {

    Optional<NewsBodyResponseDto> getNewsBodyResponseDtoByNewsId(Long newsId);

}
