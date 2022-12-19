package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.response.ResultResponseDto;
import com.kata.cinema.base.models.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface ResultMapper {

    @Mapping(source = "countRightAnswer", target = "countRightAnswer")
    @Mapping(source = "result", target = "result")
    ResultResponseDto toDTO(Result result);

    List<ResultResponseDto> toDtosList(List<Result> result);

}

