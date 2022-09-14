package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.ReviewResponseDto;



public interface ReviewResponseDtoService {

    PageDto<ReviewResponseDto> getReviewResponseDto(Long movieID,Long count);
}
