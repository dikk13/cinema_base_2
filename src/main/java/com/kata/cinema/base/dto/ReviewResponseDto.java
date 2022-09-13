package com.kata.cinema.base.dto;

import com.kata.cinema.base.enums.ReviewSortType;
import com.kata.cinema.base.enums.TypeReview;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class ReviewResponseDto {
    private ReviewSortType reviewSortType;
    private Long id;
    private TypeReview typeReview;
    private String title;
    private String description;
    private String fullName;        // - конкатенация имени и фамлии
    private LocalDate date;


    public ReviewResponseDto(ReviewSortType reviewSortType, Long id, TypeReview typeReview, String title, String description, String fullName, LocalDate date) {
        this.reviewSortType = reviewSortType.DATE_ASC;
        this.id = id;
        this.typeReview = typeReview;
        this.title = title;
        this.description = description;
        this.fullName = fullName;
        this.date = date;
    }
}
