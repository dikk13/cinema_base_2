package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.QuestionAnswerRequestDto;
import com.kata.cinema.base.models.QuestionAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface QuestionAnswerMapper {

    @Mapping(source = "questionId", target = "question.id")
    @Mapping(source = "answerId", target = "answer.id")
    QuestionAnswer toQuestionAnswer(QuestionAnswerRequestDto questionAnswerRequestDto);

    List<QuestionAnswer> fromDTO(List<QuestionAnswerRequestDto> questionAnswerRequestDtos);

}
