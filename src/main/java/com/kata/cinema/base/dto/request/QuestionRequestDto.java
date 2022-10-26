package com.kata.cinema.base.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequestDto {

    private Integer position;
    private String question;
    private List<AnswerRequestDto> answers;
    private List<ResultRequestDto> results;
}
