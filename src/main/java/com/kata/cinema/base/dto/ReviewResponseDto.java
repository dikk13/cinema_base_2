package com.kata.cinema.base.dto;

import com.kata.cinema.base.enums.TypeReview;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class ReviewResponseDto {
    private Long id;
    private TypeReview typeReview;
    private String title;
    private String description;
    private String fullName;        // - конкатенация имени и фамлии
    private LocalDate date;

}
