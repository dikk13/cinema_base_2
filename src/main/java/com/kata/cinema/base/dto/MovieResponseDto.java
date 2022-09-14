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
    protected String name;
    protected String originalName;
    protected Integer time;  // перевести в Integer
    protected LocalDate dateRelease;
    protected String countries;
//    protected String genres;    // тащить
//    protected String director;  // тащить
//    protected String roles;     // тащить
}
