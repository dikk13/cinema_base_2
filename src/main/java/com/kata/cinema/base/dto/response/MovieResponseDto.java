package com.kata.cinema.base.dto.response;

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
    private LocalDate dateRelease;
    private String countries;
    private String genres;
    private String directors;
    private String roles;

    public MovieResponseDto(Long id, String name, String originalName, String time, LocalDate dateRelease, String countries) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.time = Integer.valueOf(time);
        this.dateRelease = dateRelease;
        this.countries = countries;
    }

    public MovieResponseDto(Long id, LocalDate dateRelease, Integer time){
        this.id = id;
        this.dateRelease = dateRelease;
        this.time = time;
    }
}
