package com.kata.cinema.base.service.dto;


import com.kata.cinema.base.dto.response.ReviewResponseDto;

import java.util.List;


public interface ReviewResponseDtoService extends PaginationDtoService<ReviewResponseDto> {

    List<ReviewResponseDto> getAllUnmoderatedReviewsList();

}
