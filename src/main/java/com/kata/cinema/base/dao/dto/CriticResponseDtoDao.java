package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dto.response.CriticReviewResponseDto;
import com.kata.cinema.base.models.CriticalReview;

import java.util.List;
import java.util.Optional;

public interface CriticResponseDtoDao extends AbstractDao<Long,CriticalReview> {
    CriticReviewResponseDto getCriticResponseDtoById(Long id);
    
    List<CriticReviewResponseDto> getAllReviews();

    Optional<CriticalReview> getById(Long id);

    void deleteById(Long id);


}
