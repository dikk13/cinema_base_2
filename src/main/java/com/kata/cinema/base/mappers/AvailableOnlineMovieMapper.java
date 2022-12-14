package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.AvailableOnlineMovieRequestDto;
import com.kata.cinema.base.models.AvailableOnlineMovie;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface AvailableOnlineMovieMapper {
    AvailableOnlineMovieRequestDto toDto (AvailableOnlineMovie availableOnlineMovie);
    List<AvailableOnlineMovieRequestDto> toDTOList(List<AvailableOnlineMovie> list);
    AvailableOnlineMovie toAvailableOnlineMovie (AvailableOnlineMovieRequestDto availableOnlineMovieRequestDto);

}

