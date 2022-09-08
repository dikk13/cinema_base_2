package com.kata.cinema.base.converter;

import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.models.dto.GenreResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenreMapper {
    GenreMapper CONVERT = Mappers.getMapper(GenreMapper.class);
    GenreResponseDto toDTO (Genre genre);
    Genre toGenre(GenreResponseDto genreResponseDto);
}
