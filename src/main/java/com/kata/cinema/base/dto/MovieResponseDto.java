package com.kata.cinema.base.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MovieResponseDto {
    private Long id;
    private String name;
    private String originalName;
    private Integer time;  // перевести в Integer
    private String  dateRelease;
    private String countries;
    private String genres;
    private String directors;
    private String roles;
}
