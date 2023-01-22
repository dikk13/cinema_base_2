package com.kata.cinema.base.dto.response;

import com.kata.cinema.base.models.enums.TypeReview;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CriticReviewResponseDto {
    private Long id;
    private TypeReview typeReview;
    private String title;
    private String description;
    private Integer score;
}
