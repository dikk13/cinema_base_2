package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.ExcertionRequestDto;
import com.kata.cinema.base.dto.response.ExcertionResponseDto;
import com.kata.cinema.base.models.Excertion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ExcertionMapper {

    @Mapping(source = "description", target = "description")
    Excertion toExcertion(ExcertionRequestDto excertionRequestDto);

    ExcertionRequestDto toExcertionRequestDto(Excertion excertion);

    ExcertionResponseDto toExcertionResponseDto(Excertion excertion);


}
