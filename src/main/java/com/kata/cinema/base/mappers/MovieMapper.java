package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.MovieRequestDto;
import com.kata.cinema.base.models.Movie;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "countries", target = "countries")
    @Mapping(source = "dateRelease", target = "dateRelease")
    @Mapping(source = "rars", target = "rars")
    @Mapping(source = "mpaa", target = "mpaa")
    @Mapping(source = "time", target = "time")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "originalName", target = "originalName")
    @Mapping(source = "genreIds", target = "genres")
    Movie toMovie(MovieRequestDto movieRequestDto);
}
