package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.dto.GenreResponseDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreResponseDto toDTO (Genre genre);
    Genre toGenre(GenreResponseDto genreResponseDto);
}