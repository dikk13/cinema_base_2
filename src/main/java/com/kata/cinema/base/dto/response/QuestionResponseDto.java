package com.kata.cinema.base.dto.response;

import lombok.*;

@Data
public class QuestionResponseDto {

    private final Long id;
    private final Integer position;
    private final String question;

}

