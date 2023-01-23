package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.request.CriticReviewRequestDto;
import com.kata.cinema.base.dto.response.CriticReviewResponseDto;
import com.kata.cinema.base.models.CriticalReview;

import java.util.List;
import java.util.Optional;

public interface CriticResponseDtoService {
    CriticReviewResponseDto getCriticResponseDtoById(Long id);

    List<CriticReviewResponseDto> getAllReviews();

    Optional<CriticalReview> getById(Long id);

    void deleteById(Long id);

    void updateById (Long id, CriticReviewRequestDto criticReviewRequestDto);
}
