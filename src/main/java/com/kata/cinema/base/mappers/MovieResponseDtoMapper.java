package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.MovieResponseDto;
import com.kata.cinema.base.models.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface MovieResponseDtoMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "originalName", target = "originalName")
    @Mapping(source = "dateRelease", target = "dateRelease")
    @Mapping(expression = "java(movie.timeToInt(movie.getTime()))", target = "time")
    @Mapping(source = "countries", target = "countries")

    MovieResponseDto mapMovieToDto (Movie movie);

}