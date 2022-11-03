package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.NewsBodyResponseDto;

public interface NewsBodyResponseDtoDao {

    NewsBodyResponseDto getNewsBodyResponseDtoByNewsId(Long newsId);

}
