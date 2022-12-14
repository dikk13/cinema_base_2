package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.response.NominationResponseDto;
import com.kata.cinema.base.models.Nomination;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface NominationMapper {

    List<NominationResponseDto> toDtoList(List<Nomination>nominations);
}
