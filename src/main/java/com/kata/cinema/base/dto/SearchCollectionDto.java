package com.kata.cinema.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;



@Setter
@Getter
@NoArgsConstructor
public class SearchCollectionDto {

    private String name;
    private String url;
    private Integer countMovies;

    public SearchCollectionDto(String name) {
        this.name = name;

    }
}
