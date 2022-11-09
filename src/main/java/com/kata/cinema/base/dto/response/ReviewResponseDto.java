package com.kata.cinema.base.dto.response;

import com.kata.cinema.base.models.enums.TypeReview;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private TypeReview typeReview;
    private String title;
    private String description;
    private String fullName;
    private LocalDate date;
    private Integer countRatingLike;
    private Integer countRatingDislike;



}
