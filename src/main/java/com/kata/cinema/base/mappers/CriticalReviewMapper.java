package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.CriticReviewRequestDto;
import com.kata.cinema.base.dto.response.CriticReviewResponseDto;
import com.kata.cinema.base.models.CriticalReview;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CriticalReviewMapper {

    CriticReviewRequestDto toDto(CriticalReview criticalReview);

    List<CriticReviewResponseDto> toDtoList(List<CriticalReview> criticalReviews);

    CriticalReview toCriticalReview(CriticReviewRequestDto criticReviewRequestDto);
}
