package com.kata.cinema.base.dto.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class SearchResponseDto {


    private List<SearchMovieDto> movies;
    private List<SearchCollectionDto> collections;
    private List<SearchPersonDto> persons;

    public SearchResponseDto(List<SearchMovieDto> movies, List<SearchCollectionDto> collections, List<SearchPersonDto> persons) {
        this.movies = movies;
        this.collections = collections;
        this.persons = persons;
    }
}
