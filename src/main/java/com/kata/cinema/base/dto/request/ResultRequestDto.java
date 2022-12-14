package com.kata.cinema.base.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultRequestDto {

    private Integer countRightAnswer;
    private String result;

}
