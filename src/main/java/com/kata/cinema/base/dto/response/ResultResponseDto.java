package com.kata.cinema.base.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ResultResponseDto {

    private Integer countRightAnswer;
    private String result;

}

