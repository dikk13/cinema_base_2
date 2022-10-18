package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.AnswerRequestDto;
import com.kata.cinema.base.models.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface AnswerMapper {

    @Mapping(source = "answer", target = "answer")
    @Mapping(source = "isRight", target = "isRight")
    Answer toAnswer (AnswerRequestDto answerRequestDto);
}
