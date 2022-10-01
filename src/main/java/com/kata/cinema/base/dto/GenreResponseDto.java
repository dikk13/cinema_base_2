package com.kata.cinema.base.dto;

import com.kata.cinema.base.models.Genre;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreResponseDto {
    private Long id;
    private String name;

    public GenreResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public GenreResponseDto() {
    }


    public GenreResponseDto(Genre genre) {
        id = getId();
        name = getName();

    }

}
