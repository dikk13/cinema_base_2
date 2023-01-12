package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.AwardCeremonyRequestDto;
import com.kata.cinema.base.models.AwardCeremony;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AwardCeremonyMapper {
    @Mapping(source = "dateEvent", target = "dateEvent")
    @Mapping(source = "placeEvent", target = "placeEvent")
    AwardCeremony toAwardCeremony (AwardCeremonyRequestDto personRequestDto);
}
