package com.kata.cinema.base.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ScoreMovieResponseDto {
    private Long id;
    private Integer score;
    private LocalDate date;
    private Integer time;
    private Long movieId;
    private String name;
    private String originalName;
    private LocalDate dateRelease;
    private Double avgScore;
    private Long countScore;

}
