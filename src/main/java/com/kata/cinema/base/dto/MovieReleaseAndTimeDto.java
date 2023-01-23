package com.kata.cinema.base.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class MovieReleaseAndTimeDto {

    private Long id;
    private LocalDate dateRelease;
    private int time;

    public MovieReleaseAndTimeDto(Long id, LocalDate dateRelease, int time){
        this.id = id;
        this.dateRelease = dateRelease;
        this.time = time;
    }
}
