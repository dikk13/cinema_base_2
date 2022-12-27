package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.AwardsCeremonyResultRequestDto;
import com.kata.cinema.base.models.AwardCeremonyResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AwardCeremonyResultMapper {

    @Mapping(source = "personId", target = "person.id")
    @Mapping(source = "movieId", target = "movie.id")
    @Mapping(source = "nominationId", target = "nomination.id")
    @Mapping(source = "nominationStatus", target = "nominationStatus")
    AwardCeremonyResult toResult(AwardsCeremonyResultRequestDto requestDto);

}
