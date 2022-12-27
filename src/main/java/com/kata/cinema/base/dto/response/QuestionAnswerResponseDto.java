package com.kata.cinema.base.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerResponseDto {
    Long id;
    Integer position;
    String question;
    List<AnswerResponseDto> answers;

    public QuestionAnswerResponseDto(Long id, Integer position, String question) {
        this.id = id;
        this.position = position;
        this.question = question;
    }
}
