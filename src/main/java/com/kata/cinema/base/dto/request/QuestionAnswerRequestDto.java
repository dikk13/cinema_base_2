package com.kata.cinema.base.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class QuestionAnswerRequestDto {

    private Long questionId;

    private List<Long> answerId; // правильный ответ может быть один или несколько

}

