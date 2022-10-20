package com.kata.cinema.base.mappers;

import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.dto.response.GenreResponseDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreResponseDto toDTO (Genre genre);

    List<GenreResponseDto> toDTOList(List<Genre> list);
    Genre toGenre(GenreResponseDto genreResponseDto);
}
