package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.QuestionRequestDto;
import com.kata.cinema.base.models.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(source = "position", target = "position")
    @Mapping(source = "question", target = "question")
    Question toQuestion (QuestionRequestDto questionRequestDto);
}
