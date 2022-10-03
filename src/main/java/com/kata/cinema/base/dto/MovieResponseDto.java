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

    public MovieResponseDto(Long id, String name, String originalName, String time, String dateRelease, String countries) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.time = Integer.valueOf(time);
        this.dateRelease = dateRelease;
        this.countries = countries;
    }
}
