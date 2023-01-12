package com.kata.cinema.base.mappers;


import com.kata.cinema.base.dto.request.AwardRequestDto;
import com.kata.cinema.base.models.Award;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AwardMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    Award toAward (AwardRequestDto personRequestDto);
}
