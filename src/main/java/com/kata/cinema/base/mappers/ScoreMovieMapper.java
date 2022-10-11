package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.ScoreMovieResponseDto;
import com.kata.cinema.base.models.Score;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ScoreMovieMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "score", target = "score")
    @Mapping(source = "date", target = "date")
    Score toScore(ScoreMovieResponseDto scoreMovieResponseDto);
}
