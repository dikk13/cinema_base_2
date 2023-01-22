package com.kata.cinema.base.dto.request;

import com.kata.cinema.base.models.enums.TypeReview;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CriticReviewRequestDto {
    private TypeReview typeReview;
    private String title;
    private String description;
    private Integer score;
}
