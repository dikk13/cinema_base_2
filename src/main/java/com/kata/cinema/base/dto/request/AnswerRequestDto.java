package com.kata.cinema.base.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerRequestDto {

    private String answer;
    private Boolean isRight;

}
