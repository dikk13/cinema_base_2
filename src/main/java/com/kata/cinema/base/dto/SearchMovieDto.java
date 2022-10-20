package com.kata.cinema.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
public class SearchMovieDto {

    private Long id;
    private String name;
    private String originalName;
    private String previewUrl;
    private LocalDate date;
    private Integer avgScore;

    public SearchMovieDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
