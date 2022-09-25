package com.kata.cinema.base.dto.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class SearchResponseDto {


    private List<SearchMovieDto> movies;
    private List<SearchCollectionDto> collections;
    private List<SearchPersonDto> persons;



}
