package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.MovieScheduleRequestDto;
import com.kata.cinema.base.dto.response.MovieScheduleResponseDto;
import com.kata.cinema.base.models.MovieSchedule;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface MovieScheduleMapper {

    MovieSchedule toMovieSchedule(MovieScheduleRequestDto movieScheduleRequestDto);

    MovieScheduleResponseDto toDto (MovieSchedule movieSchedule);

    List<MovieScheduleResponseDto> toDTOList(List<MovieSchedule> list);

    MovieSchedule toMovieSchedule(MovieScheduleResponseDto movieScheduleResponseDto);
}
