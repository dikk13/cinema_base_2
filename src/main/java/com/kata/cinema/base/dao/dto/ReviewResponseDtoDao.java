package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.ReviewResponseDto;

import java.util.List;


public interface ReviewResponseDtoDao extends PaginationDtoDao<ReviewResponseDto> {

    List<ReviewResponseDto> getAllUnmoderatedReviewsList();

}
