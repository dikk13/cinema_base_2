package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.AnswerRequestDto;
import com.kata.cinema.base.dto.request.QuestionRequestDto;
import com.kata.cinema.base.dto.request.ResultRequestDto;
import com.kata.cinema.base.dto.response.QuestionResponseDto;
import com.kata.cinema.base.models.Answer;
import com.kata.cinema.base.models.Question;
import com.kata.cinema.base.models.Result;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(source = "position", target = "position")
    @Mapping(source = "question", target = "question")
    Question toQuestion (QuestionRequestDto questionRequestDto);

    Answer answerRequestDtoToAnswer(AnswerRequestDto answerRequestDto);

    List<Answer> answerRequestDtoListToAnswerList(List<AnswerRequestDto> list);

    Result resultRequestDtoToResult(ResultRequestDto resultRequestDto);

    List<Result> resultRequestDtoListToResultList(List<ResultRequestDto> list);

    List<QuestionResponseDto> toDTOList(List<Question> questions);

}
