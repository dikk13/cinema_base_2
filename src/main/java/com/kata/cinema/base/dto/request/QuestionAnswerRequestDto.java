package com.kata.cinema.base.dto.request;

import lombok.*;

import java.util.List;

@Data
public class QuestionAnswerRequestDto {

    private final Long questionId;

    private final List<Long> answerId; // правильный ответ может быть один или несколько

}

