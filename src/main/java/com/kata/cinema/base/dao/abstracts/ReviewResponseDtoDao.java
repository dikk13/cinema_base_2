package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.ReviewResponseDto;

import java.util.List;

public interface ReviewResponseDtoDao {

    PageDto<ReviewResponseDto> getReviewResponseDto(Long movieID,Long count);
}
