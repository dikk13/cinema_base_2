package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.QuestionResponseDto;
import com.kata.cinema.base.models.News;

public interface QuestionResponseDtoService extends PaginationDtoService<QuestionResponseDto>{

    News findNews(Long newsId);
}
