package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.MovieRequestDto;
import com.kata.cinema.base.models.Movie;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MovieMapper {
    Movie toMovie(MovieRequestDto movieRequestDto);
}
